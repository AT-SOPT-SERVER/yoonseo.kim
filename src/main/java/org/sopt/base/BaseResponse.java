package org.sopt.base;

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

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(true, 200, data, "요청에 성공하였습니다.");
    }

    public static <T> BaseResponse<T> success(int code, T data) {
        return new BaseResponse<>(true, code, data, "요청에 성공하였습니다.");
    }

    public static <T> BaseResponse<T> error(int code, String message) {
        return new BaseResponse<>(false, code, null, message);
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
