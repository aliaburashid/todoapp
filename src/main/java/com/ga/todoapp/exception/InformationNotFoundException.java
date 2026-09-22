package com.ga.todoapp.exception;

public class InformationNotFoundException extends RuntimeException {

    public InformationNotFoundException(String message) {
        super(message);
    }
}