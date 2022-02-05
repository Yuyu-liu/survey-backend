package com.survey.backend.domain.security;

import java.security.Key;

public class Token {
    String tokenId;
    Key key;

    public Token(String tokenId, Key key) {
        this.tokenId = tokenId;
        this.key = key;
    }

    public Token() {}

    public String getTokenId() {
        return tokenId;
    }
}
