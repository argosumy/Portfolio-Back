package com.portfolio.features.cv_features.select_cv.repositories.mappers;

import com.portfolio.models.cv_blocks.Experience;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@Component
public class ExperienceRowMapper implements RowMapper<Experience> {
    @Override
    public Experience getObject(Map<String, Object> row) {
        String title = (String) row.get("ex_title");
        LocalDate start = row.get("start_job") == null ? null : ((Date)row.get("start_job")).toLocalDate();
        LocalDate finish = row.get("finish_job") == null ? null : ((Date)row.get("finish_job")).toLocalDate();
        Experience experience = new Experience((Integer)row.get("user_id"), title, start, finish);
        experience.setDescription(row.get("ex_description") == null ? null : (String) row.get("ex_description"));
        return experience;
    }
}
