package com.mos.example.common.exception;

import com.mos.example.common.base.HttpStatusEnum;
import lombok.Getter;

/**
 * 自定义业务异常
 *
 * @author mos
 */

@Getter
public class BusinessException extends RuntimeException {

    private final HttpStatusEnum httpStatus;
    private final String message;

    public BusinessException(HttpStatusEnum httpStatus) {
        super(httpStatus.getMessage());
        this.httpStatus = httpStatus;
        this.message = httpStatus.getMessage();
    }

    public BusinessException(String message) {
        super(message);
        this.httpStatus = HttpStatusEnum.ERROR;
        this.message = message;
    }

    public BusinessException(HttpStatusEnum httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
