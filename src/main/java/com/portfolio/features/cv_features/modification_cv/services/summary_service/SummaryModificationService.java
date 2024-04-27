package com.portfolio.features.cv_features.modification_cv.services.summary_service;

import com.portfolio.features.cv_features.modification_cv.repositories.ModificationRepository;
import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryModificationService implements ModificationService<String> {
    private ModificationRepository<String> modificationRepository;

    public SummaryModificationService(@Qualifier("summaryModificationRepository") ModificationRepository<String> modificationRepository) {
        this.modificationRepository = modificationRepository;
    }

    @Override
    public void addAll(long userId, List<String> elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long add(long userId, String summary) {
        if(modificationRepository.getTableRowsByUserId(userId).isEmpty()) {
            return modificationRepository.add(userId, summary);
        } else {
            this.updateByUserId(userId, summary);
            return userId;
        }
    }

    @Override
    public void updateById(long id, String summary) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateByUserId(long userId, String summary) {
        modificationRepository.updateByUserId(userId, summary);
    }

    @Override
    public long removeById(long id) {
        return 0;
    }
}
