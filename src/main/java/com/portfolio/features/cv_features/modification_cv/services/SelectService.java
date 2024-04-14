package com.portfolio.features.cv_features.modification_cv.services;

import java.util.List;

public interface SelectService <T> {
    List<T> selectAllElementsByUserId(long userId);
}
