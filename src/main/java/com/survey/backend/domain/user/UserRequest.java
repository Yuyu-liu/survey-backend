package com.survey.backend.domain.user;

public class UserRequest {
    private final String email;
    private final String password;

    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}