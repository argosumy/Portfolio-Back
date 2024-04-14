package com.portfolio.features.cv_features.modification_cv.services;

import java.util.List;

public interface ModificationService <T>{
    void addAll(List<T> elements);
    void add(T element);
    void update(T element);
    long removeById(long id);
}
