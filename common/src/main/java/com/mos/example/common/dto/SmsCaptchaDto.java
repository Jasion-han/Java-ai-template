package com.mos.example.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 取短信验证码 DTO
 * @author Han
 */
@Data
@Accessors(chain = true)
public class SmsCaptchaDTO {

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 验证码 Key
     */
    @NotBlank(message = "验证码 Key 不能为空")
    private String captchaKey;

    /**
     * 验证码值
     */
    @NotBlank(message = "验证码值不能为空")
    private String captchaValue;
}
