package com.mos.example.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用返回结果
 *
 * @author mos
 */
@Data
@AllArgsConstructor
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}
