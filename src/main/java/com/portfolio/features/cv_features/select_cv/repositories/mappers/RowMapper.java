package com.portfolio.features.cv_features.select_cv.repositories.mappers;

import java.util.Map;

public interface RowMapper<T> {
    T getObject(Map<String, Object> row);
}
