package com.portfolio.features.user_features.registration_user.repositories;

import com.portfolio.models.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
class RegistrationUserRepositoryImpl implements RegistrationUserRepository {
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    private static final String INSERT_USER_SQL = "INSERT INTO users (first_name, last_name, email) VALUES (?, ?, ?) RETURNING id";
    private static final String SAVE_USER_PASSWORD_SQL = "INSERT INTO user_security (user_id, password) VALUES (?, ?)";
    private static final String GET_COUNTS_BY_EMAIL_OR_PHONE = "SELECT COUNT(*) FROM users WHERE email = :email OR phone = :phone";

    public RegistrationUserRepositoryImpl(NamedParameterJdbcTemplate parameterJdbcTemplate, PasswordEncoder passwordEncoder) {
        this.parameterJdbcTemplate = parameterJdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public int addNewUser(User user, String password) {
        final MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("email", user.getEmail());
        parameters.addValue("phone", user.getPhone());
        int count = parameterJdbcTemplate.queryForObject(GET_COUNTS_BY_EMAIL_OR_PHONE, parameters, Integer.class);
        if(count == 0) {
            final KeyHolder keyHolder = new GeneratedKeyHolder();
            parameterJdbcTemplate.getJdbcTemplate().update(con -> {
                PreparedStatement ps = con.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                return ps;
            }, keyHolder);
            int userId = (int) keyHolder.getKey();
            savePassword(userId, password);
            return userId;
        }
        return 0;
    }

    private void savePassword(int userId, String password) {
        String encryptedPassword = passwordEncoder.encode(password);
        parameterJdbcTemplate.getJdbcTemplate().update(con -> {
            PreparedStatement ps = con.prepareStatement(SAVE_USER_PASSWORD_SQL);
            ps.setLong(1, userId);
            ps.setString(2, encryptedPassword);
            return  ps;
        });
    }
}