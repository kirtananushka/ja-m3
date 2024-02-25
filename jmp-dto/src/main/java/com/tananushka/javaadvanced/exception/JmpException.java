package com.tananushka.javaadvanced.exception;

import lombok.Getter;

@Getter
public class JmpException extends RuntimeException {

    private final String errorCode;

    public JmpException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public JmpException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
