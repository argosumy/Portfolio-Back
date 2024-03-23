package com.portfolio.features.user_features.registration_user.repositories;

import com.portfolio.models.User;

public interface RegistrationUserRepository {
    int addNewUser(User user, String password);
}
