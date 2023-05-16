package com.example.filrougefo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CategoryControllerException extends RuntimeException{
    public CategoryControllerException(String message) {
        super(message);
    }

    public CategoryControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
