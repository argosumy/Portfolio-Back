package com.portfolio.features.cv_features.modification_cv.controllers;

import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TitleModificationController {
    private final ModificationService<String> modificationService;

    public TitleModificationController(@Qualifier("titleModificationService") ModificationService<String> modificationService) {
        this.modificationService = modificationService;
    }

    @PostMapping("/users/{userId}/title")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId) " +
            "OR hasAnyAuthority('MODIFICATION_CV')")
    public long addTitle(@RequestBody Map<String, String> data, @PathVariable("userId") long userId) {
        return modificationService.add(userId, data.get("title"));
    }

    @PutMapping("/users/{userId}/title")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId) " +
            "OR hasAnyAuthority('MODIFICATION_CV')")
    public long updateTitle(@RequestBody Map<String, String> data, @PathVariable("userId") long userId) {
        modificationService.updateByUserId(userId, data.get("title"));
        return userId;
    }
}