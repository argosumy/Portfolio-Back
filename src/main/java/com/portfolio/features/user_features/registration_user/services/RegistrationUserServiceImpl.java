package com.portfolio.features.user_features.registration_user.services;

import com.portfolio.features.user_features.registration_user.repositories.RegistrationUserRepository;
import com.portfolio.models.User;
import org.springframework.stereotype.Service;

@Service
class RegistrationUserServiceImpl implements RegistrationUserService {
    private RegistrationUserRepository repository;

    public RegistrationUserServiceImpl(RegistrationUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public int addNewUser(User user, String password) {
        return repository.addNewUser(user, password);
    }
}
