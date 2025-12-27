package com.mos.example.core.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mos.example.common.base.RPage;
import com.mos.example.common.domain.User;
import com.mos.example.common.dto.UserChangePasswordDto;
import com.mos.example.common.dto.UserDto;
import com.mos.example.common.dto.query.UserQueryDto;
import com.mos.example.common.exception.BusinessException;
import com.mos.example.common.utils.GenerateUtil;
import com.mos.example.common.utils.PageUtil;
import com.mos.example.common.vo.UserVo;
import com.mos.example.core.mapper.UserMapper;
import com.mos.example.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 用户服务实现类
 * @author ly
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Integer insert(UserDto userDto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 手机号唯一验证
        Long count = userMapper.selectCount(queryWrapper.eq(User::getPhone, userDto.getPhone()));
        if (count > 0) {
            throw new BusinessException("手机号已被绑定");
        }
        // 用户名唯一验证
        queryWrapper.clear();
        count = userMapper.selectCount(queryWrapper.eq(User::getUsername, userDto.getUsername()));
        if (count > 0) {
            throw new BusinessException("用户名已被占用");
        }

        String password = RandomUtil.randomStringLowerWithoutStr(8, "");
        String md5Password = DigestUtil.md5Hex(password).toUpperCase();
        String enPassword = passwordEncoder.encode(md5Password);

        User user = BeanUtil.copyProperties(userDto, User.class);
        user.setNumber(GenerateUtil.generateNumber("YH", 3))
                .setPassword(enPassword);
        userMapper.insert(user);
        return user.getUserId();
    }

    @Override
    public void changePassword(UserChangePasswordDto userChangePasswordDto) {
        User user = userMapper.selectById(userChangePasswordDto.getUserId());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (!passwordEncoder.matches(userChangePasswordDto.getOldPassword(), user.getPassword())) {
            throw new BusinessException("原密码错误");
        }

        userMapper.update(new LambdaUpdateWrapper<User>()
                .eq(User::getUserId, user.getUserId())
                .set(User::getPassword, passwordEncoder.encode(userChangePasswordDto.getPassword())));
    }

    @Override
    public RPage<UserVo> getPage(UserQueryDto userQueryDto) {
        Page<User> page = new Page<>(userQueryDto.getPageNo(), userQueryDto.getPageSize());
        page = PageUtil.addOrder(page, userQueryDto.getOrder());
        Page<User> pageResult = userMapper.selectPage(page, new LambdaQueryWrapper<User>()
                .eq(Objects.nonNull(userQueryDto.getNumber()), User::getNumber, userQueryDto.getNumber())
                .eq(Objects.nonNull(userQueryDto.getPhone()), User::getPhone, userQueryDto.getPhone())
                .and(Objects.nonNull(userQueryDto.getSearch()), wrapper ->
                        wrapper.like(User::getNickname, userQueryDto.getSearch())
                                .or().like(User::getUsername, userQueryDto.getSearch())));
        List<UserVo> userVoList = BeanUtil.copyToList(pageResult.getRecords(), UserVo.class);
        return RPage.success(userVoList, userQueryDto.getPageNo(), userQueryDto.getPageSize(), pageResult.getTotal());
    }

    @Override
    public UserVo getById(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return BeanUtil.copyProperties(user, UserVo.class);
    }

}
