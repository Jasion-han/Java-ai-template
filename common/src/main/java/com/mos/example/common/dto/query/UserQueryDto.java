package com.mos.example.common.dto.query;

import com.mos.example.common.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Han
 * @since 2025/5/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class UserQueryDTO extends UserDTO {

    @Schema(description = "页码", example = "1")
    private Integer pageNo = 1;

    @Schema(description = "分页条数", example = "10")
    private Integer pageSize = 10;

    @Schema(description = "排序字段+规则", example = "id asc,num desc")
    private String order;

    @Schema(description = "用户编号")
    private String number;

    @Schema(description = "复合查询：昵称/用户名")
    private String search;
}
