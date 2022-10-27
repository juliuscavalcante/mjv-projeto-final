package com.mjv.mjvracingbackend.service.exception;

import java.io.Serial;

public class DataIntegrityViolationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 110819632029639805L;

    public DataIntegrityViolationException(String message) {
        super(message);
    }

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
