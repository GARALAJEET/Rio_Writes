package com.rio.Blogging.website.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class Exceptionheading {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationError(MethodArgumentNotValidException e) {
        StringBuilder ans = new StringBuilder();

        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            ans.append(fieldName).append(": ").append(message).append("\n");
        });

        return ResponseEntity.badRequest().body(ans.toString());
    }
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<?> handleUnauthorizedException(HttpClientErrorException.Unauthorized e) {
        return new ResponseEntity<>("Unauthorized access: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
