package com.ammouri.meritis.Bank.exceptions;

public class InvalidEntityToPersistException extends Exception{
    private static final long serialVersionUID = 1L;

    public InvalidEntityToPersistException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEntityToPersistException(String message) {
        super(message);
    }
}
