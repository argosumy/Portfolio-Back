package com.portfolio.features.cv_features.modification_cv.services.experience_service;

import com.portfolio.features.cv_features.modification_cv.repositories.ModificationRepository;
import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import com.portfolio.models.cv_blocks.Experience;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceModificationService implements ModificationService<Experience> {
    private final ModificationRepository<Experience> modificationRepository;

    public ExperienceModificationService(ModificationRepository<Experience> modificationRepository) {
        this.modificationRepository = modificationRepository;
    }

    @Override
    public void addAll(List<Experience> elements) {
        elements.forEach(this::add);
    }

    @Override
    public void add(Experience element) {
        modificationRepository.add(element);
    }

    @Override
    public void update(Experience element) {
    }

    @Override
    public long removeById(long id) {
        return modificationRepository.deleteById(id);
    }
}
