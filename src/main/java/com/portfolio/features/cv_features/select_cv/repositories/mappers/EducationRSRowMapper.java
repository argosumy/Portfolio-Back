package com.portfolio.features.cv_features.select_cv.repositories.mappers;

import com.portfolio.models.cv_blocks.Education;
import com.portfolio.models.cv_blocks.EducationType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EducationRSRowMapper implements org.springframework.jdbc.core.RowMapper<Education>{
    @Override
    public Education mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getInt("id");
        long userId = rs.getInt("user_id");
        EducationType type = EducationType.valueOf(rs.getString("type"));
        String specialization = rs.getString("specialization");
        String degree = rs.getString("degree");
        LocalDate start = rs.getDate("start_education") == null
                ? null
                : rs.getDate("start_education").toLocalDate();
        LocalDate finish = rs.getDate("finish_education") == null
                ? null
                : rs.getDate("finish_education").toLocalDate();
        Education edu = new Education(userId);
        edu.setId(id);
        edu.setType(type);
        edu.setSpecialization(specialization);
        edu.setDegree(degree);
        edu.setStartEducation(start);
        edu.setFinishEducation(finish);
        return edu;
    }
}