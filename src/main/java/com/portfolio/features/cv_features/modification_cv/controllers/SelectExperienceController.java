package com.portfolio.features.cv_features.modification_cv.controllers;

import com.portfolio.features.cv_features.modification_cv.services.SelectService;
import com.portfolio.models.cv_blocks.Experience;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SelectExperienceController {
    private final SelectService<Experience> experienceSelectService;

    public SelectExperienceController(SelectService<Experience> experienceSelectService) {
        this.experienceSelectService = experienceSelectService;
    }

    @GetMapping("/users/{userId}/exp")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId) " +
            "OR hasAnyAuthority('DIRECTOR', 'MODIFICATION_ADMIN')")
    public List<Experience> getAllExperienceByUserId(@PathVariable("userId") long userId) {
        return experienceSelectService.selectAllElementsByUserId(userId);
    }
}
