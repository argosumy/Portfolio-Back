package com.portfolio.features.cv_features.select_cv.controllers;

import com.portfolio.features.cv_features.select_cv.services.CVService;
import com.portfolio.models.CurriculumVitae;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CVController {
    private final CVService cvService;

    public CVController(CVService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/users/{id}/cv")
    public CurriculumVitae getCVbyUserId(@PathVariable("id") long userId) {
        return cvService.getCVbyUserId(userId);
    }

    @GetMapping("/users/{id}/cv-modification")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId) " +
            "OR hasAnyAuthority('MODIFICATION_ACCESS')")
    public CurriculumVitae getCVModificationByUserId(@PathVariable("id") long userId) {
        return cvService.getCVbyUserId(userId);
    }

    @PutMapping("/users/{id}/cv-access")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId) " +
            "OR hasAnyAuthority('MODIFICATION_ACCESS')")
    public long manageCVAccess(@PathVariable("id") long userId, @RequestBody Map<String, Boolean> access) {
        boolean cvHidden = access.get("cvHidden");
        return cvService.manageCVAccess(userId, cvHidden);
    }

}
