package com.mos.example.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 修改密码DTO
 *
 * @author ly
 */
@Data
@Accessors(chain = true)
public class UserChangePasswordDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @Schema(description = "原密码")
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;

    @Schema(description = "密码")
    @NotBlank(message = "新密码不能为空")
    private String password;
}
