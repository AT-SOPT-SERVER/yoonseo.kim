package org.sopt.global.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, 400, "잘못된 입력입니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "요청한 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버에 오류가 발생했습니다."),

    EMPTY_POST_TITLE(HttpStatus.BAD_REQUEST, 1000, "제목이 비어 있습니다."),
    INVALID_POST_TITLE_LENGTH(HttpStatus.BAD_REQUEST, 1001, "제목은 최대 30자까지 가능합니다."),
    DUPLICATE_POST_TITLE(HttpStatus.BAD_REQUEST, 1002, "이미 존재하는 제목입니다."),
    MINIMUM_LIMIT_POST_CREATE(HttpStatus.BAD_REQUEST, 1003, "새로운 게시글은 마지막 게시글 작성 이후 3분 뒤에 작성할 수 있습니다."),
    POST_NOT_FOUND(HttpStatus.BAD_REQUEST, 1004, "존재하지 않는 게시글 ID입니다."),
    POST_DELETE_NOT_FOUND(HttpStatus.BAD_REQUEST, 1005, "삭제할 게시글이 존재하지 않습니다."),
    EMPTY_POST_CONTENT(HttpStatus.BAD_REQUEST, 1006, "내용이 비어 있습니다.");

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
