package com.portfolio.features.cv_features.modification_cv.repositories;

import java.util.List;

public interface SelectRepository <T> {
    List<T> getAllElementsByUserId(long userId);
}
