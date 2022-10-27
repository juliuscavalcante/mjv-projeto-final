package com.mjv.mjvracingbackend.controller.exception;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    @Serial
    private static final long serialVersionUID = 6405666163666033158L;

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addErros(String fieldName, String message) {
        this.erros.add(new FieldMessage(fieldName, message));
    }
}
