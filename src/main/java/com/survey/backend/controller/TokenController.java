package com.survey.backend.controller;

import com.survey.backend.domain.security.TokenService;
import com.survey.backend.domain.security.exception.TokenNotFoundException;
import com.survey.backend.domain.user.exception.NoExistingUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Boolean> verifyToken(@PathVariable String userId, @RequestBody String token) throws NoExistingUserException, TokenNotFoundException {
        this.tokenService.authenticateToken(userId, token);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}

