package com.portfolio.features.cv_features.modification_cv.services.experience_service;

import com.portfolio.features.cv_features.modification_cv.repositories.SelectRepository;
import com.portfolio.features.cv_features.modification_cv.services.SelectService;
import com.portfolio.models.cv_blocks.Experience;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectExperienceService implements SelectService<Experience> {
    private final SelectRepository<Experience> selectRepository;

    public SelectExperienceService(SelectRepository<Experience> selectRepository) {
        this.selectRepository = selectRepository;
    }

    @Override
    public List<Experience> selectAllElementsByUserId(long userId) {
        return selectRepository.getAllElementsByUserId(userId);
    }
}
