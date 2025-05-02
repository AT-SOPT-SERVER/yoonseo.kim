package org.sopt.global.common.response;

import org.springframework.http.HttpStatus;

public enum SuccessCode {
    CREATE_POST(HttpStatus.CREATED, 201, "게시글 생성에 성공했습니다."),
    GET_ALL_POSTS(HttpStatus.OK, 200, "전체 게시글 조회에 성공했습니다."),
    GET_POST(HttpStatus.OK, 200, "게시글 상세 조회에 성공했습니다."),
    UPDATE_POST(HttpStatus.OK, 200, "게시글 수정에 성공했습니다."),
    DELETE_POST(HttpStatus.OK, 200, "게시글 삭제에 성공했습니다."),
    SEARCH_POSTS(HttpStatus.OK, 200, "게시글 검색에 성공했습니다."),

    REGISTER_USER(HttpStatus.CREATED, 201, "회원가입에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final int status;
    private final String message;

    SuccessCode(HttpStatus httpStatus, int status, String message) {
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
