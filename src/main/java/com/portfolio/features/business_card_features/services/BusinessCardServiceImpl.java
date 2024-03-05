package com.portfolio.features.business_card_features.services;

import com.portfolio.features.business_card_features.repositories.UsersCardRepository;
import com.portfolio.models.BusinessCardUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessCardServiceImpl implements BusinessCardService{
    private final UsersCardRepository usersCardRepository;

    public BusinessCardServiceImpl(UsersCardRepository usersCardRepository) {
        this.usersCardRepository = usersCardRepository;
    }

    @Override
    public BusinessCardUser getCardByUserId(long userId) {
        return usersCardRepository.getUserCardByUserId(userId);
    }

    @Override
    public List<BusinessCardUser> getCards() {
        return new ArrayList<>(usersCardRepository.getUsersCardNotHidden());
    }
}
