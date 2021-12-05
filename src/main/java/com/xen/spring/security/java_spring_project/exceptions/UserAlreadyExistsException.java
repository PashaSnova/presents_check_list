package com.xen.spring.security.java_spring_project.exceptions;

public class UserAlreadyExistsException extends Exception {

    private String message;

    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
