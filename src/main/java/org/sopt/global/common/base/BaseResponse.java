package org.sopt.global.common.base;

import org.sopt.global.common.exception.ErrorCode;
import org.sopt.global.common.response.SuccessCode;

public class BaseResponse<T> {
    private final boolean success;
    private final int code;
    private final T data;
    private final String message;

    private BaseResponse(boolean success, int code, T data, String message) {
        this.success = success;
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> BaseResponse<T> success(SuccessCode code, T data) {
        return new BaseResponse<>(true, code.getStatus(), data, code.getMessage());
    }

    public static <T> BaseResponse<T> error(ErrorCode code) {
        return new BaseResponse<>(false, code.getStatus(), null, code.getMessage());
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
