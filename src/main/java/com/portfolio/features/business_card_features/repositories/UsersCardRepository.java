package com.portfolio.features.business_card_features.repositories;

import com.portfolio.models.BusinessCardUser;

import java.util.Collection;

public interface UsersCardRepository {
    BusinessCardUser getUserCardByUserId(long id);
    Collection<BusinessCardUser> getUsersCard();
    Collection<BusinessCardUser>getUsersCardNotHidden();
}
