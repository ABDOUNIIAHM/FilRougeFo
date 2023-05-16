package com.example.filrougefo.exception;

public class CategoryControllerException extends RuntimeException{
    public CategoryControllerException(String message) {
        super(message);
    }

    public CategoryControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
