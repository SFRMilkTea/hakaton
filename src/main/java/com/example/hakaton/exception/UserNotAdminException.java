package com.example.hakaton.exception;

public class UserNotAdminException extends Exception {
    public UserNotAdminException(String message) {
        super(message);
    }
}