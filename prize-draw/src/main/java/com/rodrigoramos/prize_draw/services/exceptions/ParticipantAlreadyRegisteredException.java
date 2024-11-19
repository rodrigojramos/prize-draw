package com.rodrigoramos.prize_draw.services.exceptions;

public class ParticipantAlreadyRegisteredException extends RuntimeException {
    public ParticipantAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
