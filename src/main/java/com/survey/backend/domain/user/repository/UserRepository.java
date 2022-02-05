package com.survey.backend.domain.user.repository;

import com.survey.backend.domain.user.User;
import com.survey.backend.domain.user.exception.NoExistingUserException;
import java.io.IOException;

public interface UserRepository {

    void add(User user) throws IOException;

    User find(String email) throws NoExistingUserException;

    User findId(String userId) throws  NoExistingUserException;
}