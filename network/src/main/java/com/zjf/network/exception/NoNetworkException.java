package com.zjf.network.exception;

/**
 * 没有网络异常
 */
public class NoNetworkException extends Exception {
    public static final String MSG_NO_NETWORK = "没有连接到网络,请检查";

    @Override
    public String getMessage() {
        return MSG_NO_NETWORK;
    }
}
