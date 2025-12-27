package com.mos.example.server.controller;

import com.mos.example.common.dto.SmsCaptchaDTO;
import com.mos.example.common.dto.UserLoginDTO;
import com.mos.example.common.dto.UserSmsLoginDTO;
import com.mos.example.common.utils.UserUtil;
import com.mos.example.common.vo.UserInfo;
import com.mos.example.core.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 认证接口
 *
 * @author Han
 */
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "001.认证")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/getUserInfo")
    @Operation(summary = "获取当前用户信息")
    public UserInfo getUserInfo() {
        return UserUtil.getUserInfo();
    }

    @GetMapping("/getPicCaptcha")
    @Operation(summary = "获取图片验证码")
    public Map<String, String> getPicCaptcha() {
        return authService.getPicCaptcha();
    }

    @PostMapping("/getSmsCaptcha")
    @Operation(summary = "获取短信验证码")
    public void getSmsCaptcha(@RequestBody @Valid SmsCaptchaDTO smsCaptchaDTO) {
        authService.getSmsCaptcha(smsCaptchaDTO);
    }

    @PostMapping("login")
    @Operation(summary = "密码登录")
    public UserInfo login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return authService.login(userLoginDTO);
    }

    @PostMapping("smsLogin")
    @Operation(summary = "短信验证码登录")
    public UserInfo smsLogin(@RequestBody @Valid UserSmsLoginDTO userSmsLoginDTO) {
        return authService.smsLogin(userSmsLoginDTO);
    }

}
