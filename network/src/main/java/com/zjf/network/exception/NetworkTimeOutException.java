package com.zjf.network.exception;

/**
 * 网络连接超时异常
 */
public class NetworkTimeOutException extends Exception {
    public static final String MSG_NETWORK_TIMEOUT = "网络连接超时,请重试";

    @Override
    public String getMessage() {
        return MSG_NETWORK_TIMEOUT;
    }
}
