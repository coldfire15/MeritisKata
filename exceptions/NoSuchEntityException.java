package com.ammouri.meritis.Bank.exceptions;

public class NoSuchEntityException extends Exception{
    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}
