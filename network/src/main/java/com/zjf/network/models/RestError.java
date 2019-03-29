package com.zjf.network.models;

import android.text.TextUtils;

public class RestError {
    //errCode,就是响应体中json中的code字段的value
    private int code;
    private String message;
    //网络异常
    private int errorType ;

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int pErrorType) {
        errorType = pErrorType;
    }

    public RestError(String msg) {
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return TextUtils.isEmpty(message) ? "" : message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

}
