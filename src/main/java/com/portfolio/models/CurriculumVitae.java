package com.portfolio.models;

import com.portfolio.models.cv_blocks.Education;
import com.portfolio.models.cv_blocks.Experience;

import java.util.*;
import java.util.stream.Collectors;

public class CurriculumVitae {
    private final User user;
    private final String title;
    private String summary;
    private final List<String> hardSkills;
    private final List<String> softSkills;
    private final List<Experience> experiences;
    private final List<Education> educations;

    protected CurriculumVitae(User user, String title) {
        this.hardSkills = new ArrayList<>();
        this.softSkills = new ArrayList<>();
        this.experiences = new ArrayList<>();
        this.educations = new ArrayList<>();
        this.user = user;
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getHardSkills() {
        return hardSkills;
    }

    public void addHardSkills(Collection<String> hardSkills) {
        this.hardSkills.addAll(hardSkills);
    }

    public void addHardSkills(String skills, String separator) {
            addHardSkills(separateSkills(skills, separator));
    }

    public List<String> getSoftSkills() {
        return softSkills;
    }

    public void addSoftSkills(Collection<String> softSkills) {
        this.softSkills.addAll(softSkills);
    }

    public void addSoftSkills(String skills, String separator) {
        addHardSkills(separateSkills(skills, separator));
    }

    private List<String> separateSkills(String skills, String separator) {
        return Arrays.stream(skills.split(separator))
                .map(String::trim).collect(Collectors.toList());
    }

    protected void addEducations(Collection<Education> educations) {
        this.educations.addAll(educations);
    }

    protected void addExperiences(Collection<Experience> experiences) {
        this.experiences.addAll(experiences);
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
