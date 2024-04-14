package com.portfolio.helpers.repository_helpers.repository_convertors;

import org.springframework.stereotype.Component;

@Component("titlesRepositoryFieldConverter")
public class TitlesRepositoryFieldConverter implements RepositoryFieldConverter{
    @Override
    public String convertToFieldNameDB(String fieldName) {
        return switch (fieldName) {
            case "title" -> "title";
            case "userId" -> "user_id";
            case "summary" -> "summary";
            case "id" -> "id";
            default -> "";
        };
    }
}
