package com.portfolio.features.user_features.modification_user.repositories;

import com.portfolio.features.user_features.modification_user.repositories.mapers.UserRowMapper;
import com.portfolio.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ModificationUserRepositoryImpl implements ModificationUserRepository {
    private final JdbcTemplate jdbcTemplate;

    public ModificationUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(long id) {
        try {
            String sql = "SELECT * FROM users WHERE id=?";
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException ignored) {
            return User.EMPTY_USER;
        }
    }

    @Override
    @Transactional
    public int updateUser(String sql) {
        if(!sql.isEmpty()) {
            return jdbcTemplate.update(sql);
        }
        return 0;
    }

    @Override
    @Transactional
    public int deleteUserById(long id) {
        final String sql = "DELETE FROM users WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<User> getAllUsers() {
        final String sql = "SELECT * FROM users;";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
}