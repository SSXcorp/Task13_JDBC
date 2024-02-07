package com.example.demo.Exceptions;

public class SameUserEmailException extends Exception{
    public SameUserEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameUserEmailException(String message) {
        super(message);
    }
}
