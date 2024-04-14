package com.portfolio.models.cv_blocks;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Education {
    private long id;
    private long userId;
    private String nameInstitute;
    private String specialization;
    private String degree;
    private LocalDate startEducation;
    private LocalDate finishEducation;
    private EducationType type;

    public Education(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNameInstitute() {
        return nameInstitute;
    }

    public void setNameInstitute(String nameInstitute) {
        this.nameInstitute = nameInstitute;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public LocalDate getStartEducation() {
        return startEducation;
    }

    public void setStartEducation(LocalDate startEducation) {
        this.startEducation = startEducation;
    }

    public LocalDate getFinishEducation() {
        return finishEducation;
    }

    public void setFinishEducation(LocalDate finishEducation) {
        this.finishEducation = finishEducation;
    }

    public EducationType getType() {
        return type;
    }

    public void setType(EducationType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return userId == education.userId &&
                Objects.equals(nameInstitute, education.nameInstitute) &&
                Objects.equals(specialization, education.specialization) &&
                Objects.equals(degree, education.degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, nameInstitute, specialization, degree);
    }

    @Override
    public String toString() {
        return "Education{" +
                "userId=" + userId +
                ", nameInstitute='" + nameInstitute + '\'' +
                ", specialization='" + specialization + '\'' +
                ", degree='" + degree + '\'' +
                ", startEducation=" + startEducation.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                ", finishEducation=" + finishEducation.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                '}';
    }
}
