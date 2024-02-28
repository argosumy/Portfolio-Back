package com.portfolio.models;

import java.util.List;

public final class BusinessCardUser {
    private final User user;
    private final List<String> practiceAreas;

    public BusinessCardUser(User user, List<String> practiceAreas) {
        this.user = user;
        this.practiceAreas = practiceAreas;
    }

    public User getUser() {
        return user;
    }

    public List<String> getPracticeAreas() {
        return practiceAreas;
    }

    @Override
    public String toString() {
        return "BusinessCardUser{" +
                "user=" + user +
                ", practiceAreas=" + practiceAreas +
                '}';
    }
}
