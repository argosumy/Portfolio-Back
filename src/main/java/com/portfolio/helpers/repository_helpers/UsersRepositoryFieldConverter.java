package com.portfolio.helpers.repository_helpers;

import org.springframework.stereotype.Component;

@Component("usersRepositoryFieldConverter")
public class UsersRepositoryFieldConverter implements RepositoryFieldConverter {
    @Override
    public String convertToFieldNameDB(String fieldName) {
        return switch (fieldName) {
            case "id" -> "id";
            case "firstName" -> "first_name";
            case "lastName" -> "last_name";
            case "phone" -> "phone";
            case "email" -> "email";
            case "location" -> "location";
            case "photo" -> "photo";
            default -> "";
        };
    }
}
