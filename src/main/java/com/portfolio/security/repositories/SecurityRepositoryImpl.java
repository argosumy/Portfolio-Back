package com.portfolio.security.repositories;

import com.portfolio.security.models.UserSecurity;
import com.portfolio.security.repositories.mappers.UserDetailsRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityRepositoryImpl implements SecurityRepository{
    private final JdbcTemplate jdbcTemplate;

    public SecurityRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails getUserDetails(String login) {
        try {
            final String sql = "SELECT * FROM users inner join user_security ON users.id = user_security.user_id WHERE email = ?";
            return jdbcTemplate.queryForObject(sql, new UserDetailsRowMapper(), login);
        } catch (EmptyResultDataAccessException ignored) {
            return UserSecurity.USER_SECURITY_EMPTY;
        }
    }
}
