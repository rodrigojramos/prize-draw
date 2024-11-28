package com.rodrigoramos.prize_draw.dto;

public class LogoutRequest {
    private String token;

    public LogoutRequest() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
