package com.portfolio.features.user_features.modification_user.services;

import com.portfolio.models.User;
import org.springframework.stereotype.Service;

@Service
public interface ModificationUserService {
    User updateUser(User user);
    int deleteUserById(long id);
    User getUserById(long id);
}