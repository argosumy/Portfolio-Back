package com.portfolio.models;

import java.util.List;
import java.util.Objects;

public class BusinessCardUser {
    private final User user;
    private final String title;
    private final List<String> practiceAreas;
    private boolean isHidden;

    public BusinessCardUser(User user, String title, List<String> practiceAreas) {
        this.user = user;
        this.title = title;
        this.practiceAreas = practiceAreas;
    }

    public User getUser() {
        return user;
    }

    public List<String> getPracticeAreas() {
        return practiceAreas;
    }

    public String getTitle() {
        return title;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    @Override
    public String toString() {
        return "BusinessCardUser{" +
                "user=" + user +
                ", title='" + title + '\'' +
                ", practiceAreas=" + practiceAreas +
                ", isHidden=" + isHidden +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessCardUser cardUser = (BusinessCardUser) o;
        return user.equals(cardUser.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
