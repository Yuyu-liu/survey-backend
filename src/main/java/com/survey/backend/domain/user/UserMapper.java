package com.survey.backend.domain.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse from(User user) {
        return new UserResponse(user.getToken().getTokenId(), user.getId());
    }
}
