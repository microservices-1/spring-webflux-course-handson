package com.acme.reactive.handson.dto;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse validationError(IllegalArgumentException e) {
        return new ErrorResponse(e.getMessage());
    }
}
