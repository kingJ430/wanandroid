package com.zjf.network.exception;

import java.io.IOException;

public class ErrorException extends IOException {

    public ErrorException() {
    }

    public ErrorException(String message) {
        super(message);
    }
}
