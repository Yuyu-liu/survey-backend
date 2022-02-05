package com.survey.backend.domain.user;

public class UserResponse {
    private final String token;
    private final String userId;

    public UserResponse(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }
}