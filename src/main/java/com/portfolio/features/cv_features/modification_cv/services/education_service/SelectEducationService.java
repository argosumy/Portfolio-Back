package com.portfolio.features.cv_features.modification_cv.services.education_service;

import com.portfolio.features.cv_features.modification_cv.repositories.SelectRepository;
import com.portfolio.features.cv_features.modification_cv.services.SelectService;
import com.portfolio.models.cv_blocks.Education;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectEducationService implements SelectService<Education> {
    private final SelectRepository<Education> selectRepository;

    public SelectEducationService(SelectRepository<Education> selectRepository) {
        this.selectRepository = selectRepository;
    }

    @Override
    public List<Education> selectAllElementsByUserId(long userId) {
        return selectRepository.getAllElementsByUserId(userId);
    }
}
