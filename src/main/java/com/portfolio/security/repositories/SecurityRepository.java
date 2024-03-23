package com.portfolio.security.repositories;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityRepository {
    UserDetails getUserDetails(String login);
}
