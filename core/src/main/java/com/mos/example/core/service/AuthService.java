package com.mos.example.core.service;

import com.mos.example.common.dto.SmsCaptchaDTO;
import com.mos.example.common.dto.UserLoginDTO;
import com.mos.example.common.dto.UserSmsLoginDTO;
import com.mos.example.common.vo.UserInfo;

import java.util.Map;

/**
 * 认证服务
 * @author Han
 */
public interface AuthService {

    /**
     * 获取图片验证码
     *
     * @return 包含验证码 Key 和 Base64 编码图片的 Map
     */
    Map<String, String> getPicCaptcha();

    /**
     * 获取短信验证码
     *
     * @param smsCaptchaDTO 包含手机号、验证码 Key 和验证码值的 DTO
     */
    void getSmsCaptcha(SmsCaptchaDTO smsCaptchaDTO);

    /**
     * 密码登录
     *
     * @param userLoginDTO 包含手机号和密码的 DTO
     * @return 用户信息
     */
    UserInfo login(UserLoginDTO userLoginDTO);

    /**
     * 短信验证码登录
     *
     * @param userSmsLoginDTO 包含手机号、验证码 Key 和验证码值的 DTO
     * @return 用户信息
     */
    UserInfo smsLogin(UserSmsLoginDTO userSmsLoginDTO);

}
