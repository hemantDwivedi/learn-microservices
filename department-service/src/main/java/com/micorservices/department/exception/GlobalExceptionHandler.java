package com.micorservices.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotException(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "DEPARTMENT NOT FOUND"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DepartmentCodeAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(DepartmentCodeAlreadyExistsException exception, WebRequest request){
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "DEPARTMENT CODE ALREADY EXISTS"
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest request){
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "INTERNAL SERVER ERROR"
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
