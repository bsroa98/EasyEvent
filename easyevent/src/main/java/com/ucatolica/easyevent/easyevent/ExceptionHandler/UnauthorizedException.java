package com.ucatolica.easyevent.easyevent.ExceptionHandler;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
