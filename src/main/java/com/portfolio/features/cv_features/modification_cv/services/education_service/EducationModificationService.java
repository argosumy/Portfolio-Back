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
    public void addAll(long userId, List<Education> elements) {
        elements.forEach(x -> this.add(userId, x));
    }

    @Override
    public long add(long userId, Education element) {
        return modificationRepository.add(userId, element);
    }

    @Override
    public long removeById(long id) {
        return modificationRepository.deleteById(id);
    }

    @Override
    public void updateById(long id, Education element) {

    }

    @Override
    public void updateByUserId(long id, Education element) {
        throw new UnsupportedOperationException();
    }
}
