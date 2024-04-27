package com.portfolio.features.cv_features.modification_cv.repositories;


import java.util.List;
import java.util.Map;

public interface ModificationRepository <T> {
    long add(long userId,T element);
    long deleteById(long id);
    void updateByUserId(long userId, T element);
    List<Map<String, Object>> getTableRowsByUserId(long userId);
}
