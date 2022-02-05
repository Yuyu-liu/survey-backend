package com.survey.backend.domain.security;

import com.survey.backend.domain.security.exception.TokenNotFoundException;
import com.survey.backend.domain.user.User;
import com.survey.backend.domain.user.exception.NoExistingUserException;
import com.survey.backend.domain.user.repository.UserRepositoryInMemory;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenManager tokenManager;
    private final UserRepositoryInMemory userRepositoryInMemory;

    public TokenService(TokenManager tokenManager, UserRepositoryInMemory userRepositoryInMemory) {
        this.tokenManager = tokenManager;
        this.userRepositoryInMemory = userRepositoryInMemory;
    }

    public void authenticateToken(String userId, String token) throws NoExistingUserException, TokenNotFoundException {
        User user = this.userRepositoryInMemory.findId(userId);
        if (!user.getToken().getTokenId().equals(token)) {
            throw new TokenNotFoundException();
        }
        this.tokenManager.verifyToken(user.getToken(), user.getId());
    }
}