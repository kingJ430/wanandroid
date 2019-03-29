package com.zjf.network.exception;

/**
 * 网络错误异常
 */
public class NetworkErrorException extends RuntimeException {
    public static final String MSG_NETWORK_ERROR = "网络连接异常";

    @Override
    public String getMessage() {
        return MSG_NETWORK_ERROR;
    }
}
