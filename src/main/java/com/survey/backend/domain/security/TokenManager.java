package com.survey.backend.domain.security;

import com.survey.backend.domain.security.exception.TokenNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {

    public Token generateToken(String userId) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jwsToken =  Jwts.builder().setExpiration(Date.from(ZonedDateTime.now().plusHours(2).toInstant())).setSubject(userId).signWith(key).compact();

        return new Token(jwsToken, key);
    }

    public void verifyToken(Token token, String userId) throws TokenNotFoundException {
        boolean valid =
            Jwts.parserBuilder().setSigningKey(token.key).build().parseClaimsJws(token.tokenId).getBody().getSubject()
                .equals(userId);
        if (!valid) {
            throw new TokenNotFoundException();
        }
    }
}