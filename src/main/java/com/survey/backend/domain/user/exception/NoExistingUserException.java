package com.survey.backend.domain.user.exception;

public class NoExistingUserException extends Exception{
    public NoExistingUserException() {
        super("NON_EXISTING_USER");
    }
}
