package com.Management.Students.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResoureNotFound extends RuntimeException {
    public ResoureNotFound(String message) {
        super(message);
    }
}
