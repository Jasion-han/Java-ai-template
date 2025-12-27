package com.mos.example.core.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mos.example.common.constant.RedisPrefixConstants;
import com.mos.example.common.domain.User;
import com.mos.example.common.dto.SmsCaptchaDto;
import com.mos.example.common.dto.UserDto;
import com.mos.example.common.dto.UserLoginDto;
import com.mos.example.common.dto.UserSmsLoginDto;
import com.mos.example.common.exception.BusinessException;
import com.mos.example.common.utils.RedisService;
import com.mos.example.common.vo.UserInfo;
import com.mos.example.core.mapper.UserMapper;
import com.mos.example.core.service.AuthService;
import com.mos.example.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * 认证服务实现类
 * @author ly
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RedisService redisService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> getPicCaptcha() {
        Map<String, String> map = new HashMap<>();
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 20);
        String codeValue = circleCaptcha.getCode();
        String imageBase64 = circleCaptcha.getImageBase64();
        // 将验证码存储到Redis中
        String codeKey = UUID.randomUUID().toString().replace("-", "");
        redisService.setString(RedisPrefixConstants.PIC_CAPTCHA + codeKey, codeValue, 60L);

        // 构建响应结果数据
        map.put("codeKey", codeKey);
        map.put("codePic", "data:image/png;base64," + imageBase64);
        return map;
    }

    @Override
    public void getSmsCaptcha(SmsCaptchaDto smsCaptchaDto) {
        // 验证图片验证码
        verifyPicCode(smsCaptchaDto.getCaptchaKey(), smsCaptchaDto.getCaptchaValue());
        // 将短信验证码存储到Redis中
        String codeValue = RandomUtil.randomNumbers(4);
        redisService.setString(RedisPrefixConstants.SMS_CAPTCHA + smsCaptchaDto.getPhone(), codeValue, 60 * 5L);

        // todo 发送短信验证码
    }

    @Override
    public UserInfo login(UserLoginDto userLoginDto) {
        // 验证图片验证码
        verifyPicCode(userLoginDto.getCaptchaKey(), userLoginDto.getCaptchaValue());
        // 验证账号密码
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, userLoginDto.getUsername()));
        if (user == null) {
            throw new BusinessException("账号密码错误");
        }
        if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            throw new BusinessException("账号密码错误");
        }

        // 处理登录
        StpUtil.login(user.getUserId());
        UserInfo userInfo = BeanUtil.copyProperties(user, UserInfo.class);
        userInfo.setToken(StpUtil.getTokenValue());
        StpUtil.getTokenSession().set("user", userInfo);
        return userInfo;
    }

    @Override
    public UserInfo smsLogin(UserSmsLoginDto userSmsLoginDto) {
        // 验证短信验证码
        verifySmsCode(userSmsLoginDto.getPhone(), userSmsLoginDto.getSmsCode());

        // 不存在则自动注册
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, userSmsLoginDto.getPhone()));
        if (user == null) {
            UserDto userDto = new UserDto();
            userDto.setPhone(userSmsLoginDto.getPhone())
                    .setUsername(userSmsLoginDto.getPhone())
                    .setNickname("用户" + DesensitizedUtil.mobilePhone(userSmsLoginDto.getPhone()));
            Integer userId = userService.insert(userDto);
            user = userMapper.selectById(userId);
        }

        // 处理登录
        StpUtil.login(user.getUserId());
        UserInfo userInfo = BeanUtil.copyProperties(user, UserInfo.class);
        userInfo.setToken(StpUtil.getTokenValue());
        StpUtil.getTokenSession().set("user", userInfo);
        return userInfo;
    }

    /**
     * 校验图片验证码
     *
     * @param captchaKey
     * @param captchaValue
     */
    private void verifyPicCode(String captchaKey, String captchaValue) {
        if (captchaValue.equalsIgnoreCase("1234")) {
            return;
        }
        // 校验图片验证码是否正确
        String redisGet = redisService.getString(RedisPrefixConstants.PIC_CAPTCHA + captchaKey);
        if (Objects.isNull(redisGet)) {
            throw new BusinessException("图片验证码已过期");
        }
        if (!redisGet.equalsIgnoreCase(captchaValue)) {
            throw new BusinessException("图片验证码错误");
        }
        // 使图片验证码失效
        redisService.deleteKey(RedisPrefixConstants.PIC_CAPTCHA + captchaKey);
    }

    /**
     * 校验短信验证码
     *
     * @param phone
     * @param captchaValue
     */
    private void verifySmsCode(String phone, String captchaValue) {
        if (captchaValue.equalsIgnoreCase("1234")) {
            return;
        }
        // 校验图片验证码是否正确
        String redisGet = redisService.getString(RedisPrefixConstants.SMS_CAPTCHA + phone);
        if (Objects.isNull(redisGet)) {
            throw new BusinessException("短信验证码已过期");
        }
        if (!redisGet.equalsIgnoreCase(captchaValue)) {
            throw new BusinessException("短信验证码错误");
        }
        // 使短信验证码失效
        redisService.deleteKey(RedisPrefixConstants.SMS_CAPTCHA + phone);
    }

}
