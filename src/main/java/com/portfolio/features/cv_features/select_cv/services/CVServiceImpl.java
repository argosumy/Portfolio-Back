package com.portfolio.features.cv_features.select_cv.services;

import com.portfolio.features.cv_features.select_cv.repositories.CVRepository;
import com.portfolio.features.cv_features.select_cv.repositories.mappers.RowMapper;
import com.portfolio.models.CVBuilder;
import com.portfolio.models.CVBuilderImpl;
import com.portfolio.models.CurriculumVitae;
import com.portfolio.models.User;
import com.portfolio.models.cv_blocks.Education;
import com.portfolio.models.cv_blocks.Experience;
import com.portfolio.models.cv_blocks.SkillsType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CVServiceImpl implements CVService {
    private final CVRepository cvRepository;
    private final RowMapper<Education> educationRowMapper;
    private final RowMapper<Experience> experienceRowMapper;

    public CVServiceImpl(CVRepository cvRepository,
                         RowMapper<Education> educationRowMapper,
                         RowMapper<Experience> experienceRowMapper) {
        this.cvRepository = cvRepository;
        this.educationRowMapper = educationRowMapper;
        this.experienceRowMapper = experienceRowMapper;
    }

    @Override
    public CurriculumVitae getCVbyUserId(long userId) {
        List<Map<String, Object>> response = cvRepository.getFullDataByUserId(userId);
        User user = null;
        CVBuilder cvBuilder = null;
        for(Map<String, Object> entry : response) {
            if(user == null) {
                user = new User((String)entry.get("first_name"), (String)entry.get("last_name"), (String)entry.get("email"));
                user.setId(userId);
                user.setLocation(entry.get("location") == null ? null : (String) entry.get("location"));
                user.setPhone(entry.get("phone") == null ? null : (String) entry.get("phone"));
                user.setPhoto(entry.get("photo") == null ? null : (String) entry.get("photo"));
                String title = entry.get("job_title") == null ? null : (String) entry.get("job_title");
                String summary = entry.get("summary") == null ? null : ((String) entry.get("summary"));
                cvBuilder = new CVBuilderImpl(user, title);
                cvBuilder.setSummary(summary);
            }
            if(entry.get("s_type") != null && entry.get("s_type").equals(SkillsType.HARD.name())) {
                cvBuilder.addHardSkills((String)entry.get("s_name"), ", ");
            }
            if(entry.get("s_type") != null && entry.get("s_type").equals(SkillsType.SOFT.name())) {
                cvBuilder.addSoftSkills((String)entry.get("s_name"), ", ");
            }
            cvBuilder.addEducation(educationRowMapper.getObject(entry));
            cvBuilder.addExperience(experienceRowMapper.getObject(entry));
        }
        assert cvBuilder != null;
        return cvBuilder.createCV();
    }
}