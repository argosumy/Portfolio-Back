package com.portfolio.features.business_card_features.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;


public class PractiseAreasUserRepositoryImpl implements PractiseAreasUserRepository {
    private final JdbcTemplate jdbcTemplate;

    public PractiseAreasUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<String> getPracticesByUserId(long id) {
        final List<String> areas = new ArrayList<>();
        try {
            final String sql = "SELECT * FROM hard_skills WHERE id=?";
            return jdbcTemplate.query(sql, resultSet -> {
                while(resultSet.next()) {
                    areas.add(resultSet.getString("name"));
                }
                return areas;
            }, id);
        } catch (EmptyResultDataAccessException ignored) {
            return areas;
        }
    }
}
