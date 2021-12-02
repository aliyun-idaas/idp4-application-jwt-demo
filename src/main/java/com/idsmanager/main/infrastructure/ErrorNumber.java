package com.idsmanager.main.infrastructure;

public abstract class ErrorNumber {

    /**
     * 错误的token
     */
    public static final Integer TOKEN_WRONG = 301;


    /**
     * 请求参数检查失败
     */
    public static final Integer REQUEST_METHOD_ERROR = 305;


    private ErrorNumber() {
    }
}
