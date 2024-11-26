package com.rodrigoramos.prize_draw.services.exceptions;

public class PrizeDrawAlreadyMadeException extends RuntimeException{

    public PrizeDrawAlreadyMadeException(String msg) {
        super(msg);
    }
}
