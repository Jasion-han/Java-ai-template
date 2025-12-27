package com.mos.example.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登录用户 DTO
 *
 * @author Han
 */
@Data
@Accessors(chain = true)
public class UserSmsLoginDTO {

    @Schema(description = "电话")
    @NotBlank(message = "电话不能为空")
    private String phone;

    @Schema(description = "短信验证码")
    @NotBlank(message = "短信验证码不能为空")
    private String smsCode;
}
