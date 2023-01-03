package com.example.expensesappbackend.exception;

public class DataNotCreateException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;

    public DataNotCreateException(String message) {
        this.message = message;
    }
}
