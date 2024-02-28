package com.portfolio.features.business_card_features.repositories;

import java.util.List;

public interface PractiseAreasUserRepository {
    List<String> getPracticesByUserId(long id);
}
