package org.sopt.global.common.base;

import org.sopt.global.common.exception.CustomException;
import org.sopt.global.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Void> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        return BaseResponse.error(errorCode);
    }
}
