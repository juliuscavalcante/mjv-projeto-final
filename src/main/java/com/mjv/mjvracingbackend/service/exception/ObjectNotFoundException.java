package com.mjv.mjvracingbackend.service.exception;

import java.io.Serial;

public class ObjectNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1502622201477266736L;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
