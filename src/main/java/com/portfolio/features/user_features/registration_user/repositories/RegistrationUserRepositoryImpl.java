package com.portfolio.features.user_features.registration_user.repositories;

import com.portfolio.models.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
class RegistrationUserRepositoryImpl implements RegistrationUserRepository {
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;
    private static final String INSERT_USER_SQL = "INSERT INTO users (first_name, last_name, email) VALUES (?, ?, ?) RETURNING id";
    private static final String GET_COUNTS_BY_EMAIL_OR_PHONE = "SELECT COUNT(*) FROM users WHERE email = :email OR phone = :phone";

    public RegistrationUserRepositoryImpl(NamedParameterJdbcTemplate parameterJdbcTemplate) {
        this.parameterJdbcTemplate = parameterJdbcTemplate;
    }

    @Override
    public int addNewUser(User user) {
        final MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("email", user.getEmail());
        parameters.addValue("phone", user.getPhone());
        int count = parameterJdbcTemplate.queryForObject(GET_COUNTS_BY_EMAIL_OR_PHONE, parameters, Integer.class);
        if(count == 0) {
            final KeyHolder keyHolder = new GeneratedKeyHolder();
            parameterJdbcTemplate.getJdbcTemplate().update(con -> {
                PreparedStatement ps = con.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getFistName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                return ps;
            }, keyHolder);
            return (int) keyHolder.getKey();
        }
        return 0;
    }

}