package com.portfolio.features.business_card_features.services;

import com.portfolio.models.BusinessCardUser;

public interface BusinessCardService {
    BusinessCardUser getCardByUserId(long userId);
}
