package com.portfolio.helpers.repository_helpers.repository_convertors;

import org.springframework.stereotype.Component;

@Component("experienceRepositoryFieldConverter")
public class ExperienceRepositoryFieldConverter implements RepositoryFieldConverter{
    @Override
    public String convertToFieldNameDB(String fieldName) {
        return switch (fieldName) {
            case "userId" -> "user_id";
            case "title" -> "title";
            case "startJob" -> "start_job";
            case "finishJob" -> "finish_job";
            case "description" -> "description";
            default -> "";
        };
    }
}
