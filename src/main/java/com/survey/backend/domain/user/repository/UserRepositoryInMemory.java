package com.survey.backend.domain.user.repository;

import com.survey.backend.domain.user.User;
import com.survey.backend.domain.user.exception.NoExistingUserException;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryInMemory implements UserRepository {

    private final List<User> usersList;
    private final DataMapper dataMapper;

    public UserRepositoryInMemory(DataMapper dataMapper) throws IOException {
        this.dataMapper = dataMapper;
        this.usersList = this.dataMapper.mapFromDatabase();
    }

    @Override
    public void add(User user) throws IOException {
        this.usersList.add(user);
        this.dataMapper.mapToDatabase(this.usersList);
    }

    @Override
    public User find(String email) throws NoExistingUserException {
        return this.usersList.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElseThrow(NoExistingUserException::new);
    }

    @Override
    public User findId(String userId) throws NoExistingUserException {
        return this.usersList.stream().filter(user -> user.getId().equals(userId)).findFirst().orElseThrow(NoExistingUserException::new);
    }
}
