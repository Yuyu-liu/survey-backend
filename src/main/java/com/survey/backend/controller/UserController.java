package com.survey.backend.controller;

import com.survey.backend.domain.user.*;
import com.survey.backend.domain.user.exception.InvalidCredentialsException;
import com.survey.backend.domain.user.exception.NoExistingUserException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request)
        throws NoSuchAlgorithmException, IOException {
        User user = this.userService.createUser(request);
        UserResponse userResponse = this.userMapper.from(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String email) throws NoExistingUserException {
        User user = this.userService.findUser(email);
        UserResponse userResponse = this.userMapper.from(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) throws NoExistingUserException, NoSuchAlgorithmException,
        InvalidCredentialsException {
        User user = this.userService.login(userRequest);
        UserResponse userResponse = this.userMapper.from(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}