package com.nexus.cart.exception;

public class EntityExistsException extends RuntimeException{
    public EntityExistsException(String message) {
        super(message);
    }

    public EntityExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
