package com.portfolio.features.user_features.modification_user.services;

import com.portfolio.features.user_features.modification_user.repositories.ModificationUserRepository;
import com.portfolio.helpers.repository_helpers.RepositoryFieldConverter;
import com.portfolio.helpers.repository_helpers.SqlBuilder;
import com.portfolio.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.portfolio.helpers.repository_helpers.SqlQueries.UPDATE_USER_SQL_TEMPLATE;

@Service
public class ModificationUserServiceImpl implements ModificationUserService {
    private final ModificationUserRepository repository;
    private final RepositoryFieldConverter converter;
    private final SqlBuilder sqlBuilder;

    public ModificationUserServiceImpl(ModificationUserRepository repository, RepositoryFieldConverter converter, SqlBuilder sqlBuilder) {
        this.repository = repository;
        this.converter = converter;
        this.sqlBuilder = sqlBuilder;
    }

    @Override
    public User getUserById(long id) {
        return repository.getUserById(id);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        String sql = sqlBuilder.buildSql(UPDATE_USER_SQL_TEMPLATE, user, converter);
        if(repository.updateUser(sql) > 0) {
              return repository.getUserById(user.getId());
        }
        throw new RuntimeException();
    }

    @Override
    public int deleteUserById(long id) {
        return repository.deleteUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }
}