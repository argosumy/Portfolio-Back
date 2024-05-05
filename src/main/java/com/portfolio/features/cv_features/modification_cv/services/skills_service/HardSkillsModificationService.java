package com.portfolio.features.cv_features.modification_cv.services.skills_service;

import com.portfolio.features.cv_features.modification_cv.repositories.ModificationRepository;
import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("hardSkillsModificationService")
public class HardSkillsModificationService implements ModificationService<String> {
    private final ModificationRepository<String> hardSkillsModificationRepository;

    public HardSkillsModificationService(@Qualifier("hardSkillsModificationRepository") ModificationRepository<String> modificationRepository) {
        this.hardSkillsModificationRepository = modificationRepository;
    }

    @Override
    public void addAll(long userId, List<String> elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long add(long userId, String textSkills) {
        if(!textSkills.isBlank()) {
            System.out.println(textSkills);
            return hardSkillsModificationRepository.add(userId, textSkills);
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
            hardSkillsModificationRepository.updateByUserId(userId, textSkills);
        }
    }

    @Override
    public long removeById(long id) {
        throw new UnsupportedOperationException();
    }
}
