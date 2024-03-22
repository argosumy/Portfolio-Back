package com.portfolio.models.cv_blocks;

import java.util.Comparator;

public class EducationComparator implements Comparator<Education> {

    @Override
    public int compare(Education o1, Education o2) {
        int res = compareNullDate(o1, o2);
        if(res != 0) {
            return res;
        }
        return compareDate(o1, o2);

    }

    private int compareNullDate(Education o1, Education o2) {
        int res = 0;
        if(o1.getFinishEducation() == null) {
            res = -1;
        } else if(o2.getFinishEducation() == null) {
            res = 1;
        }
        return res;
    }

    private int compareDate(Education o1, Education o2) {
        int res = 0;
        if(o1.getFinishEducation().isAfter(o2.getFinishEducation())) {
            res = -1;
        } else if(o1.getFinishEducation().isBefore(o2.getFinishEducation())) {
            res = 1;
        }
        return res;
    }
}