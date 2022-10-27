package com.mjv.mjvracingbackend.controller.exception;

import com.mjv.mjvracingbackend.service.exception.DataIntegrityViolationException;
import com.mjv.mjvracingbackend.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity<StandardError> standardErrorResponseEntity(ObjectNotFoundException objectNotFoundException,
                                                                     HttpServletRequest httpServletRequest) {

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Object not found", objectNotFoundException.getMessage(), httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<StandardError> dataIntegrityViolationException (DataIntegrityViolationException dataIntegrityViolationException,
                                                                          HttpServletRequest httpServletRequest) {

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Data breach", dataIntegrityViolationException.getMessage(), httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationError> validationErrors (MethodArgumentNotValidException methodArgumentNotValidException,
                                                             HttpServletRequest httpServletRequest) {

        ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Validation error", "error validating fields", httpServletRequest.getRequestURI());

        for(FieldError x : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errors.addErros(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
