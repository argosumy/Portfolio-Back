package com.portfolio.features.cv_features.select_cv.repositories.mappers;

import com.portfolio.models.cv_blocks.Education;
import com.portfolio.models.cv_blocks.EducationType;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Map;

@Component
public class EducationRowMapper implements RowMapper<Education> {

    @Override
    public Education getObject(Map<String, Object> row) {
        Education education = new Education((Integer)row.get("user_id"));
        education.setId(row.get("edu_id") == null ? 0 : (Integer)row.get("edu_id"));
        education.setNameInstitute(row.get("name_institute") == null ? "" : (String)row.get("name_institute"));
        education.setSpecialization(row.get("specialization") == null ? "" : (String)row.get("specialization"));
        education.setStartEducation(row.get("start_education") == null
                ? null
                : ((Date)row.get("start_education")).toLocalDate()
                                    );
        education.setFinishEducation(row.get("finish_education") == null
                ? null
                : ((Date)row.get("finish_education")).toLocalDate()
                                    );
        education.setType(row.get("edu_type") == null ? null : EducationType.valueOf((String) row.get("edu_type")));
        return education;
    }
}
