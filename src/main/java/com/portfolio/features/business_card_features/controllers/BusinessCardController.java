package com.portfolio.features.business_card_features.controllers;

import com.portfolio.features.business_card_features.services.BusinessCardServiceImpl;
import com.portfolio.models.BusinessCardUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessCardController {
    private final BusinessCardServiceImpl cardService;

    public BusinessCardController(BusinessCardServiceImpl cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/users/{id}/card")
    public BusinessCardUser getBusinessCard(@PathVariable("id") long id) {
        return cardService.getCardByUserId(id);
    }
}
