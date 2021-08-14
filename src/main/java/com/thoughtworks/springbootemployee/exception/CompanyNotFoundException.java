package com.thoughtworks.springbootemployee.exception;

public class CompanyNotFoundException extends RuntimeException{
    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
