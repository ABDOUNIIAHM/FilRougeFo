package com.example.filrougefo.exception;

public class ClientAlreadyExistException extends RuntimeException{

    public ClientAlreadyExistException(String s) {
        super(s);
    }

    public ClientAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
