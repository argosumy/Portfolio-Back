package com.portfolio.features.cv_features.modification_cv.repositories;


public interface ModificationRepository <T> {
    long add(T element);
    long deleteById(long id);
}
