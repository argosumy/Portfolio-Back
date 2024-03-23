package com.portfolio.security.repositories.mappers;

import com.portfolio.security.models.UserSecurity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsRowMapper implements RowMapper<UserDetails> {

    @Override
    public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserSecurity(rs.getInt("user_id"), rs.getString("role"), rs.getString("password"), rs.getString("email"));
    }
}
