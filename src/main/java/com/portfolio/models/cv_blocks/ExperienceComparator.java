package com.portfolio.models.cv_blocks;

import java.util.Comparator;

public class ExperienceComparator implements Comparator<Experience> {

    @Override
    public int compare(Experience o1, Experience o2) {
        int res = compareNullDate(o1, o2);
        if(res != 0) {
            return res;
        }
        return compareDate(o1, o2);
    }

    private int compareNullDate(Experience o1, Experience o2) {
        int res = 0;
        if(o1.getFinishJob() == null) {
            res = -1;
        } else if(o2.getFinishJob() == null) {
            res = 1;
        }
        return res;
    }

    private int compareDate(Experience o1, Experience o2) {
    int res = 0;
        if(o1.getFinishJob().isAfter(o2.getFinishJob())) {
        res = -1;
    } else if(o1.getFinishJob().isBefore(o2.getFinishJob())) {
        res = 1;
    }
        return res;
    }
}