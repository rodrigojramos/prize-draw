package com.rodrigoramos.prize_draw.services.exceptions;

public class TokenNotProvidedException extends RuntimeException{

    public TokenNotProvidedException(String msg) {
        super(msg);
    }
}
