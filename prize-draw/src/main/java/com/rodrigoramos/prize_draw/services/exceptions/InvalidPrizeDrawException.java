package com.rodrigoramos.prize_draw.services.exceptions;

public class InvalidPrizeDrawException extends RuntimeException {
    public InvalidPrizeDrawException(String msg) {
        super(msg);
    }
}
