package com.example.filrougefo.exception;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(String message) {
        super(message);
    }
}