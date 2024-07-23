package com.portfolio.features.cv_features.select_cv.services;

import com.portfolio.models.CurriculumVitae;

public interface CVService {
    CurriculumVitae getCVbyUserId(long userId);
    long manageCVAccess(long userId, boolean cvHidden);
}
