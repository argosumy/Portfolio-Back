package com.portfolio.security.services;

import com.portfolio.security.repositories.SecurityRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomAuthorizationServiceByUserId {
    private final SecurityRepository repository;

    public CustomAuthorizationServiceByUserId(SecurityRepository repository) {
        this.repository = repository;
    }

    public boolean isAccountOwner(Authentication authentication, long id) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String login = userDetails.getUsername();
        Map<String, Object> result = repository.getSecurityUserById(id);
        return login.equals(result.get("email"));
    }

}
