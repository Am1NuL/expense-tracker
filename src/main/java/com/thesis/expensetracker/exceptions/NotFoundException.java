package com.thesis.expensetracker.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() { super(); }

    public NotFoundException(String message) { super(message); }

    public NotFoundException(String message, Throwable cause) { super(message, cause); }
}
