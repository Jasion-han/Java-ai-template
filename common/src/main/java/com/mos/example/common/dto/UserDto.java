package com.mos.example.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户 DTO
 *
 * @author Han
 */
@Data
@Accessors(chain = true)
public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "账号")
    @NotBlank(message = "账号不能为空")
    private String username;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(description = "联系电话")
    @NotBlank(message = "联系电话不能为空")
    private String phone;
}
