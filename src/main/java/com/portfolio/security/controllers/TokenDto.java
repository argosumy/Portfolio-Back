package com.portfolio.security.controllers;

public class TokenDto {
    private long userId;
    private String userLogin;
    private String token;

    public TokenDto() {
    }

    public TokenDto(long userId, String token, String userLogin) {
        this.userId = userId;
        this.token = token;
        this.userLogin = userLogin;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
