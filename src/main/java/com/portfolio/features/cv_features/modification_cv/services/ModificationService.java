package com.portfolio.features.cv_features.modification_cv.services;

import java.util.List;

public interface ModificationService <T>{
    void addAll(long userId, List<T> elements);
    long add(long userId, T element);
    void updateById(long id, T element);
    void updateByUserId(long userId, T element);
    long removeById(long id);
}
