package com.example.filrougefo.exception;

public class OrderLineControllerException extends RuntimeException{

    public OrderLineControllerException(String message) {
        super(message);
    }

    public OrderLineControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
