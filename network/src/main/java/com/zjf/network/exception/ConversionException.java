package com.zjf.network.exception;

/**
 * @author zjf
 * @version
 * 报文解析错误的异常
 */
public class ConversionException extends RuntimeException {
    public static final String MSG = "服务器内容解析错误!";

    @Override
    public String getMessage() {
        return MSG;
    }
}
