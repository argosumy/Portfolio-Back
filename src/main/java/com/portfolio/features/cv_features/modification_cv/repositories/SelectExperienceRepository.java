package com.portfolio.features.cv_features.modification_cv.repositories;

import com.portfolio.models.cv_blocks.Experience;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class SelectExperienceRepository implements SelectRepository <Experience> {
    private final JdbcTemplate jdbcTemplate;

    public SelectExperienceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Experience> getAllElementsByUserId(long userId) {
        String sql = "SELECT * FROM experience WHERE user_id=?;";
        return jdbcTemplate.query(sql, new RowMapper<Experience>() {
            @Override
            public Experience mapRow(ResultSet rs, int rowNum) throws SQLException {
                String title = rs.getString("title");
                LocalDate start = rs.getDate("start_job") == null
                        ? null
                        : rs.getDate("start_job").toLocalDate();
                LocalDate finish = rs.getDate("finish_job") == null ? null : rs.getDate("finish_job").toLocalDate();
                String description = rs.getString("description");
                Experience exp = new Experience(userId, title, start, finish, description);
                exp.setExpId(rs.getLong("id"));
                return exp;
            }
        } , userId);
    }
}
