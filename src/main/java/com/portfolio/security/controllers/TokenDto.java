package com.portfolio.security.controllers;

import java.util.List;

public class TokenDto {
    private long userId;
    private String userLogin;
    private List<String> permissions;
    private String token;

    public TokenDto() {
    }

    public TokenDto(long userId, String token, String userLogin, List<String> permissions) {
        this.userId = userId;
        this.token = token;
        this.userLogin = userLogin;
        this.permissions = permissions;
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

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
