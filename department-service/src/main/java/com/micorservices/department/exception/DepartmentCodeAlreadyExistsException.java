package com.micorservices.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DepartmentCodeAlreadyExistsException extends RuntimeException{
    private String message;

    public DepartmentCodeAlreadyExistsException(String message) {
        super(message);
    }
}
