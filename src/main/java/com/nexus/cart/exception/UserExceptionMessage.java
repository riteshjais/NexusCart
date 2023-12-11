package com.nexus.cart.exception;

import org.springframework.http.HttpStatus;

public class UserExceptionMessage {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public UserExceptionMessage(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }
}
