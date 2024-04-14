package com.portfolio.models;

import com.portfolio.models.cv_blocks.Education;
import com.portfolio.models.cv_blocks.EducationComparator;
import com.portfolio.models.cv_blocks.Experience;
import com.portfolio.models.cv_blocks.ExperienceComparator;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CVBuilderImpl implements CVBuilder {
    private final CurriculumVitae curriculumVitae;
    private final Set<Education> educations;
    private final Set<Experience> experiences;
    private final Set<String> hardSkills;
    private final Set<String> softSkills;


    public CVBuilderImpl(User user, String title) {
        this.educations = new HashSet<>();
        this.experiences = new HashSet<>();
        this.hardSkills = new HashSet<>();
        this.softSkills = new HashSet<>();
        this.curriculumVitae = new CurriculumVitae(user, title);
    }

    public CVBuilderImpl(CurriculumVitae curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
        this.educations = new HashSet<>(curriculumVitae.getEducations());
        this.experiences = new HashSet<>(curriculumVitae.getExperiences());
        this.hardSkills = new HashSet<>(curriculumVitae.getHardSkills());
        this.softSkills = new HashSet<>(curriculumVitae.getSoftSkills());
    }

    @Override
    public CVBuilder setSummary(String summary) {
        curriculumVitae.setSummary(summary);
        return this;
    }

    @Override
    public CVBuilder addEducation(Education education) {
        if(education != null) {
            educations.add(education);
        }
        return this;
    }

    @Override
    public CVBuilder addExperience(Experience experience) {
        if(experience != null && experience.getTitle() != null && experience.getExpId() > 0) {
            experiences.add(experience);
        }
        return this;
    }

    @Override
    public CVBuilder addHardSkills(String hardSkill) {
        if(hardSkill != null) {
            hardSkills.add(hardSkill);
        }
        return this;
    }

    @Override
    public CVBuilder addSoftSkills(String softSkill) {
        if(softSkill != null) {
            softSkills.add(softSkill);
        }
        return this;
    }

    @Override
    public CurriculumVitae createCV() {
        curriculumVitae.addEducations(educations
                .stream()
                .sorted(new EducationComparator())
                .toList());
        curriculumVitae.addExperiences(experiences
                .stream()
                .sorted(new ExperienceComparator())
                .collect(Collectors.toList()));
        curriculumVitae.addHardSkills(hardSkills);
        curriculumVitae.addSoftSkills(softSkills);
        return curriculumVitae;
    }
}