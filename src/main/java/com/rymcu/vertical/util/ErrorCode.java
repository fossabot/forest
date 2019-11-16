package com.rymcu.vertical.util;

/**
 * Created by kaenry on 2016/9/20.
 * ErrorCode
 */
public enum ErrorCode {

    ILLEGAL_PARAMS("ILLEGAL_PARAMS", "request params invalid"),
    UNAUTHORIZED("401", "请求要求用户的身份认证"),
    SERVER_ERROR("SERVER_ERROR", "server is busy");

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
