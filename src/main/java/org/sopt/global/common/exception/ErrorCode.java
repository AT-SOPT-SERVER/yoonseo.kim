package org.sopt.global.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, 400, "잘못된 입력입니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "요청한 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버에 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;

    ErrorCode(HttpStatus httpStatus, int status, String message) {
        this.httpStatus = httpStatus;
        this.status = status;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
