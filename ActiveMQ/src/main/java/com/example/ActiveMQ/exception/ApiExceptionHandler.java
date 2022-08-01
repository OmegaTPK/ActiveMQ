package com.example.ActiveMQ.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ApiExceptionModel> validateExceptionHandler(RuntimeException e) {
        ApiExceptionModel body = new ApiExceptionModel(e.getMessage(),
                Instant.now());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
