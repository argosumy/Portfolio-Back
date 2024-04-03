package com.portfolio.security.services;

import org.springframework.security.core.Authentication;

public interface CustomAuthorizationService {
    public boolean authorize(Authentication authentication);
}
