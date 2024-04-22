package com.portfolio.features.cv_features.modification_cv.services.education_service;

import com.portfolio.features.cv_features.modification_cv.repositories.ModificationRepository;
import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import com.portfolio.models.cv_blocks.Education;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationModificationService implements ModificationService<Education> {
    private final ModificationRepository<Education> modificationRepository;

    public EducationModificationService(ModificationRepository<Education> modificationRepository) {
        this.modificationRepository = modificationRepository;
    }

    @Override
    public void addAll(List<Education> elements) {
        elements.forEach(this::add);
    }

    @Override
    public void add(Education element) {
        modificationRepository.add(element);
    }

    @Override
    public void update(Education element) {
    }

    @Override
    public long removeById(long id) {
        return modificationRepository.deleteById(id);
    }
}
