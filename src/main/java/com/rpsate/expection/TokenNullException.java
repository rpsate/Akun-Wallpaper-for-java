package com.rpsate.expection;

import io.jsonwebtoken.JwtException;

public class TokenNullException extends JwtException {
    public TokenNullException(String message) {
        super(message);
    }

    public TokenNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
