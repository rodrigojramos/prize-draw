package com.rodrigoramos.prize_draw.services.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String msg) {
        super(msg);
    }
}
