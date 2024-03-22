package com.portfolio.features.cv_features.select_cv.repositories;

import java.util.List;
import java.util.Map;

public interface CVRepository {
    List<Map<String, Object>> getFullDataByUserId(long id);
}
