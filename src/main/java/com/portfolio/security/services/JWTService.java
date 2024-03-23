package com.portfolio.security.services;

public interface JWTService {
    String createJWTToken(String login, String password);
}
