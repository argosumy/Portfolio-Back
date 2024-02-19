package com.portfolio.features.user_features.modification_user.repositories;

import com.portfolio.features.user_features.modification_user.repositories.mapers.UserRowMapper;
import com.portfolio.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ModificationUserRepositoryImpl implements ModificationUserRepository {
    private final JdbcTemplate jdbcTemplate;


    public ModificationUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(long id) {
        User res = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", new UserRowMapper(), id);
        return res;
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
}