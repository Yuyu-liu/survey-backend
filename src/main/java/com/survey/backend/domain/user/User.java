package com.survey.backend.domain.user;

import com.survey.backend.domain.security.Token;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class User {

    private UUID id;
    private String email;
    private String passwordHash;
    private Token token;

    public User (UUID id, String email, String passwordHash, Token token) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.token = token;
    }
    public User() {}

    public String getId() {
        return this.id.toString();
    }

    public String getEmail() {
        return this.email;
    }

    public Token getToken() {
        return this.token;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}

