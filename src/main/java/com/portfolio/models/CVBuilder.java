package com.portfolio.models;

import com.portfolio.models.cv_blocks.Education;
import com.portfolio.models.cv_blocks.Experience;

public interface CVBuilder {
    CVBuilder addEducation(Education education);
    CVBuilder addExperience(Experience experience);
    CVBuilder addHardSkills(String hardSkill);
    CVBuilder addSoftSkills(String softSkill);
    CVBuilder setSummary(String summary);
    CurriculumVitae createCV();
}
