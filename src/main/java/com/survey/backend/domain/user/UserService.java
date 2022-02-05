package com.survey.backend.domain.user;

import com.survey.backend.domain.security.PasswordManager;
import com.survey.backend.domain.security.Token;
import com.survey.backend.domain.security.TokenManager;
import com.survey.backend.domain.user.exception.InvalidCredentialsException;
import com.survey.backend.domain.user.exception.NoExistingUserException;
import com.survey.backend.domain.user.repository.UserRepositoryInMemory;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepositoryInMemory userRepositoryInMemory;
    private final TokenManager tokenManager;
    private final PasswordManager passwordManager;

    public UserService(UserRepositoryInMemory userRepositoryInMemory, TokenManager tokenManager, PasswordManager passwordManager) {
        this.userRepositoryInMemory = userRepositoryInMemory;
        this.passwordManager = passwordManager;
        this.tokenManager = tokenManager;
    }

    public User createUser(UserRequest request) throws NoSuchAlgorithmException, IOException {
        UUID userId = UUID.randomUUID();
        String hashedPassword = this.passwordManager.hashPassword(request.getPassword());
        Token token = this.tokenManager.generateToken(userId.toString());

        User user = new User(userId, request.getEmail(),hashedPassword,token);
        this.userRepositoryInMemory.add(user);

        return user;
    }

    public User login(UserRequest userRequest) throws NoExistingUserException, NoSuchAlgorithmException,
        InvalidCredentialsException {
        User user = this.findUser(userRequest.getEmail());
        String hashedPassword = this.passwordManager.hashPassword(userRequest.getPassword());
        if (user.getPasswordHash().equals(hashedPassword)) {
            Token token = this.tokenManager.generateToken(user.getId());
            user.setToken(token);
            return user;
        } else {
            throw new InvalidCredentialsException();
        }
    }

    public User findUser(String email) throws NoExistingUserException {
        return this.userRepositoryInMemory.find(email);
    }
}