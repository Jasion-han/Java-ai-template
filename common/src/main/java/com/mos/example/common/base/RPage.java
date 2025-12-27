package com.mos.example.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 通用返回分页
 *
 * @author Han
 */
@Data
@AllArgsConstructor
public class RPage<T> {

    private List<T> data;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalCount;
    private Long totalPage;

    public static <T> RPage<T> success(List<T> list, Integer pageNo, Integer pageSize, Long totalCount) {
        Long totalPage = (totalCount + pageSize - 1) / pageSize;
        return new RPage<>(list, pageNo, pageSize, totalCount, totalPage);
    }

}
