package com.portfolio.security.services;

import com.portfolio.security.controllers.TokenDto;

public interface JWTService {
    TokenDto createJWTToken(String login, String password);
}
