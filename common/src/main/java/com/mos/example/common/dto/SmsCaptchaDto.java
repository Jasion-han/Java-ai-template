package com.mos.example.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 取短信验证码DTO
 * @author ly
 */
@Data
@Accessors(chain = true)
public class SmsCaptchaDto {

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 验证码Key
     */
    @NotBlank(message = "图片验证码key不能为空")
    private String captchaKey;

    /**
     * 验证码值
     */
    @NotBlank(message = "图片验证码不能为空")
    private String captchaValue;
}
