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
    public void addAll(long userId, List<Experience> elements) {
        elements.forEach(x -> this.add(userId, x));
    }

    @Override
    public long add(long userId, Experience exp) {
        return modificationRepository.add(userId, exp);
    }

    @Override
    public void updateById(long id, Experience exp) {

    }

    @Override
    public void updateByUserId(long id, Experience exp) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long removeById(long id) {
        return modificationRepository.deleteById(id);
    }
}
