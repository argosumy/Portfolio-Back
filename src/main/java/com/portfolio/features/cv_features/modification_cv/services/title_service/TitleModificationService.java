package com.portfolio.features.cv_features.modification_cv.services.title_service;

import com.portfolio.features.cv_features.modification_cv.repositories.ModificationRepository;
import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("titleModificationService")
public class TitleModificationService implements ModificationService<String> {
    private final ModificationRepository<String> modificationRepository;

    public TitleModificationService(@Qualifier("titleModificationRepository") ModificationRepository<String> modificationRepository) {
        this.modificationRepository = modificationRepository;
    }

    @Override
    public void addAll(long userId, List<String> elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional
    public long add(long userId, String title) {
        if(modificationRepository.getTableRowsByUserId(userId).isEmpty()) {
            modificationRepository.add(userId, title);
        } else {
            this.updateByUserId(userId, title);
        }
        return userId;
    }

    @Override
    public void updateById(long id, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateByUserId(long userId, String title) {
        modificationRepository.updateByUserId(userId, title);
    }

    @Override
    public long removeById(long id) {
        return 0;
    }


}
