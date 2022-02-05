package com.survey.backend.domain.user.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.survey.backend.domain.user.User;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DataMapper {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String path = "src/main/java/com/survey/backend/domain/user/repository/UserDatabase.json";

    public List<User> mapFromDatabase() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(path)));
        return this.mapper.readValue(json, new TypeReference<>() {
        });
    }

    public void mapToDatabase(List<User> usersList) throws IOException {
        this.mapper.writeValue(new File(path), usersList );
    }
}