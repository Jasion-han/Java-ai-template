package com.mos.example.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户视图对象
 *
 * @author Han
 */

@Data
@Accessors(chain = true)
public class UserVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer userId;

    @Schema(description = "用户编号")
    private String number;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;

}
