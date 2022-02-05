package com.survey.backend.domain.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class PasswordManager {
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(password.getBytes());
        byte[] byteData = messageDigest.digest();

        StringBuilder stringBuilder = new StringBuilder();
        for (byte byteDatum : byteData) {
            stringBuilder.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }
}