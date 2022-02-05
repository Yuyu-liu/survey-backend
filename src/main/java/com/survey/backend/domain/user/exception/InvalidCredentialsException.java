package com.survey.backend.domain.user.exception;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super("INVALID_CREDENTIALS");
    }
}
