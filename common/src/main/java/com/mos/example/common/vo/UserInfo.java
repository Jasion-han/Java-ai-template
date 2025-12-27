package com.mos.example.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 已登录用户信息
 *
 * @author Han
 */
@Data
@Accessors(chain = true)
public class UserInfo {

    @Schema(description = "token")
    private String token;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "用户编号")
    private String number;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "联系电话")
    private String phone;
}
