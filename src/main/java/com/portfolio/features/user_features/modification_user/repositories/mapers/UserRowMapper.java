package com.portfolio.features.user_features.modification_user.repositories.mapers;

import com.portfolio.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String location = rs.getString("location");
        String photo = rs.getString("photo");
        User user = new User(firstName, lastName, email);
        user.setId(id);
        user.setPhone(phone);
        user.setLocation(location);
        user.setPhoto(photo);
        return user;
    }
}
