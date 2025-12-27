package com.mos.example.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登录用户DTO类
 * @author ly
 */
@Data
@Accessors(chain = true)
public class UserLoginDto {

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(description = "图片验证码key")
    @NotBlank(message = "图片验证码key不能为空")
    private String captchaKey;

    @Schema(description = "图片验证码")
    @NotBlank(message = "图片验证码不能为空")
    private String captchaValue;
}
