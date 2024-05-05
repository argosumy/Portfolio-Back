package com.portfolio.features.cv_features.modification_cv.services.skills_service;

import com.portfolio.features.cv_features.modification_cv.repositories.ModificationRepository;
import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("softSkillsModificationService")
public class SoftSkillsModificationService implements ModificationService<String> {
    private final ModificationRepository<String> softSkillsModificationRepository;

    public SoftSkillsModificationService(@Qualifier("softSkillsModificationRepository") ModificationRepository<String> softSkillsModificationRepository) {
        this.softSkillsModificationRepository = softSkillsModificationRepository;
    }

    @Override
    public void addAll(long userId, List<String> elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long add(long userId, String textSkills) {
        if(!textSkills.isBlank()) {
            return softSkillsModificationRepository.add(userId, textSkills);
        }
        return 0;
    }

    @Override
    public void updateById(long id, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateByUserId(long userId, String textSkills) {
        if(!textSkills.isBlank()) {
            softSkillsModificationRepository.updateByUserId(userId, textSkills);
        }
    }

    @Override
    public long removeById(long id) {
        return 0;
    }
}
