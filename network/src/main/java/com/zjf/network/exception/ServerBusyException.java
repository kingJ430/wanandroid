package com.zjf.network.exception;

/**
 * 服务器繁忙异常
 */
public class ServerBusyException extends RuntimeException {
    public static final String MSG_SERVER_BUSY = "服务器繁忙!!";

    @Override
    public String getMessage() {
        return MSG_SERVER_BUSY;
    }
}
