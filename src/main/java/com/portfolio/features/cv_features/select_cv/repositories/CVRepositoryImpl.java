package com.portfolio.features.cv_features.select_cv.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CVRepositoryImpl implements CVRepository {
    private final JdbcTemplate jdbcTemplate;

    public CVRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getFullDataByUserId(long id) {
        String sql = "SELECT users.id AS user_id, first_name, last_name, phone, email, location, photo, e.id AS edu_id, name_institute, " +
                "specialization, start_education, finish_education, e.description AS edu_description, e.type AS edu_type," +
                "ex.id as ex_id, ex.title as ex_title, start_job, finish_job, ex.description AS ex_description, ss.name AS ss_name, " +
                "hs.name AS hs_name, titles.title AS job_title, summary FROM users " +
                "LEFT JOIN education AS e ON users.id = e.user_id " +
                "LEFT JOIN experience AS ex on users.id = ex.user_id " +
                "LEFT JOIN hard_skills AS hs ON users.id = hs.user_id " +
                "LEFT JOIN soft_skills AS ss ON users.id = ss.user_id " +
                "LEFT JOIN titles ON users.id = titles.user_id " +
                "WHERE users.id = ?";
        return jdbcTemplate.queryForList(sql, id);
    }


}
