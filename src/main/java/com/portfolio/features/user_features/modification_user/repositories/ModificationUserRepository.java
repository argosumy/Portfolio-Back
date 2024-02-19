package com.portfolio.features.user_features.modification_user.repositories;

import com.portfolio.models.User;

public interface ModificationUserRepository {
    int updateUser(String sql);
    int deleteUserById(long id);
    User getUserById(long id);

}
