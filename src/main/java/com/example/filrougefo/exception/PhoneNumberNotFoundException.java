package com.example.filrougefo.exception;

public class PhoneNumberNotFoundException extends RuntimeException {
    public PhoneNumberNotFoundException(String message) {
        super(message);
    }
    public PhoneNumberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
