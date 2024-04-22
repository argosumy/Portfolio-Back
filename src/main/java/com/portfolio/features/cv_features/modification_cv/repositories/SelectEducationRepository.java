package com.portfolio.features.cv_features.modification_cv.repositories;

import com.portfolio.features.cv_features.select_cv.repositories.mappers.EducationRSRowMapper;
import com.portfolio.models.cv_blocks.Education;
import com.portfolio.models.cv_blocks.EducationType;
import com.portfolio.models.cv_blocks.Experience;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class SelectEducationRepository implements SelectRepository<Education> {
    private final JdbcTemplate jdbcTemplate;

    public SelectEducationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Education> getAllElementsByUserId(long userId) {
        String sql = "SELECT * FROM education WHERE user_id=?;";
        return jdbcTemplate.query(sql, new EducationRSRowMapper(), userId);
    }
}
