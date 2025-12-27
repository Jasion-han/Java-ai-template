package com.mos.example.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登录用户 DTO
 * @author Han
 */
@Data
@Accessors(chain = true)
public class UserLoginDTO {

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(description = "验证码 Key")
    @NotBlank(message = "验证码 Key 不能为空")
    private String captchaKey;

    @Schema(description = "验证码值")
    @NotBlank(message = "验证码值不能为空")
    private String captchaValue;
}
