package com.portfolio.features.business_card_features.services;

import com.portfolio.features.business_card_features.repositories.PractiseAreasUserRepository;
import com.portfolio.features.user_features.modification_user.repositories.ModificationUserRepository;
import com.portfolio.models.BusinessCardUser;
import org.springframework.stereotype.Service;

@Service
public class BusinessCardServiceImpl implements BusinessCardService{
    private final ModificationUserRepository modificationUserRepository;
    private final PractiseAreasUserRepository areasUserRepository;

    public BusinessCardServiceImpl(ModificationUserRepository modificationUserRepository, PractiseAreasUserRepository areasUserRepository) {
        this.modificationUserRepository = modificationUserRepository;
        this.areasUserRepository = areasUserRepository;
    }

    @Override
    public BusinessCardUser getCardByUserId(long userId) {
        return new BusinessCardUser(modificationUserRepository.getUserById(userId),
                areasUserRepository.getPracticesByUserId(userId));
    }

}
