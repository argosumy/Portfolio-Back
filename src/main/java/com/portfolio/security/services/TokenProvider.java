package com.portfolio.security.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenProvider {
    String generateAccessToken(UserDetails userDetails);
    String validateToke(String token);
}
