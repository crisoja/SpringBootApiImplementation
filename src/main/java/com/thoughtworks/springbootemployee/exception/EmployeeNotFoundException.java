package com.thoughtworks.springbootemployee.exception;

public class EmployeeNotFoundException extends RuntimeException{
    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
