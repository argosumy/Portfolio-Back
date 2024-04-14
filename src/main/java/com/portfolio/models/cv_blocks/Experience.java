package com.portfolio.models.cv_blocks;

import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.Objects;

public class Experience implements Comparable<Experience>{
    private long expId;
    private long userId;
    private String title;
    private LocalDate startJob;
    private LocalDate finishJob;
    private String description;
    public static final Experience EMPTY_EXPERIENCE = new Experience();

    private Experience() {
        this.expId = 0;
        this.userId = 0;
        this.title = "";
        this.description = "";
        this.finishJob = LocalDate.of(1900, 1, 1);
        this.startJob = LocalDate.of(1900, 1, 1);
    }

    public Experience(long expId) {
        this.expId = expId;
    }

    public Experience(long userId, String title, LocalDate startJob, LocalDate finishJob, String description) {
        this.userId = userId;
        this.title = title == null ? "" : title;
        this.startJob = startJob;
        this.finishJob = finishJob;
        this.description = description == null ? "" : description;
    }

    public long getExpId() {
        return expId;
    }

    public void setExpId(long expId) {
        this.expId = expId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartJob() {
        return startJob;
    }

    public void setStartJob(LocalDate startJob) {
        this.startJob = startJob;
    }

    public LocalDate getFinishJob() {
        return finishJob;
    }

    public void setFinishJob(LocalDate finishJob) {
        this.finishJob = finishJob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(@NonNull Experience o) {
        if(finishJob == null || o.finishJob == null) {
            if(finishJob == null && o.finishJob == null) {
                return 0;
            }
            if(finishJob == null) {
                return -1;
            } else {
                return 1;
            }
        }
        if(finishJob.isBefore(o.finishJob)) {
            return 1;
        }
        if(finishJob.isAfter(o.finishJob)) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return userId == that.userId &&
                Objects.equals(title, that.title) &&
                Objects.equals(startJob, that.startJob) &&
                Objects.equals(finishJob, that.finishJob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, title, startJob, finishJob);
    }

    @Override
    public String toString() {
        return "Experience{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", startJob=" + startJob +
                ", finishJob=" + finishJob +
                ", description='" + description + '\'' +
                '}';
    }
}
