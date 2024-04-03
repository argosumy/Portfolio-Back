package com.portfolio.security.repositories;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface SecurityRepository {
    UserDetails getUserDetails(String login);
    Map<String, Object> getSecurityUserById(long id);
}
