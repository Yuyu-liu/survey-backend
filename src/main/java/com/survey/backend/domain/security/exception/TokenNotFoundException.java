package com.survey.backend.domain.security.exception;

public class TokenNotFoundException extends Exception{
    public TokenNotFoundException() {
        super("TOKEN_NOT_FOUND");
    }
}
