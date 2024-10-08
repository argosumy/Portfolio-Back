package com.portfolio.features.cv_features.modification_cv.controllers;

import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SummaryModificationController {
    private final ModificationService<String> modificationService;

    public SummaryModificationController(@Qualifier("summaryModificationService") ModificationService<String> modificationService) {
        this.modificationService = modificationService;
    }

    @PostMapping("/users/{userId}/summary")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId) " +
            "OR hasAnyAuthority('MODIFICATION_CV')")
    public long addSummary(@RequestBody Map<String, String> data, @PathVariable("userId") long userId) {
        return modificationService.add(userId, data.get("summary"));
    }

    @PutMapping("/users/{userId}/summary")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId) " +
            "OR hasAnyAuthority('MODIFICATION_CV')")
    public long updateSummary(@RequestBody Map<String, String> data, @PathVariable("userId") long userId) {
        modificationService.updateByUserId(userId, data.get("summary"));
        return userId;
    }


}
