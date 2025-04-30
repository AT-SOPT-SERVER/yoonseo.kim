package org.sopt.global.common.base;

import org.sopt.global.common.exception.ErrorCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse<Void> handleBadRequest() {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        return BaseResponse.error(errorCode);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse<Void> handleNotFound() {
        ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        return BaseResponse.error(errorCode);
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<Void> handleServerError() {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        return BaseResponse.error(errorCode);
    }
}
