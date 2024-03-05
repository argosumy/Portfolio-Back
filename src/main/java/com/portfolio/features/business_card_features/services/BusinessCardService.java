package com.portfolio.features.business_card_features.services;

import com.portfolio.models.BusinessCardUser;

import java.util.List;

public interface BusinessCardService {
    BusinessCardUser getCardByUserId(long userId);
    List<BusinessCardUser> getCards();
}
