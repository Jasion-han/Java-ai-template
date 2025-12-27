package com.mos.example.common.exception;

import com.mos.example.common.base.HttpStatusEnum;
import lombok.Getter;

/**
 * 自定义业务异常
 *
 * @author Han
 */

@Getter
public class BusinessException extends RuntimeException {

    private final HttpStatusEnum httpStatus;
    private final String message;

    /**
     * 构造函数
     *
     * @param httpStatus 枚举类型的 HTTP 状态码
     */
    public BusinessException(HttpStatusEnum httpStatus) {
        super(httpStatus.getMessage());
        this.httpStatus = httpStatus;
        this.message = httpStatus.getMessage();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public BusinessException(String message) {
        super(message);
        this.httpStatus = HttpStatusEnum.ERROR;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param httpStatus 枚举类型的 HTTP 状态码
     * @param message    异常消息
     */
    public BusinessException(HttpStatusEnum httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
