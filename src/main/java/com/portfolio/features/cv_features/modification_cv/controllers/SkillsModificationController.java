package com.portfolio.features.cv_features.modification_cv.controllers;

import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class SkillsModificationController {
    private final ModificationService<String> hardSkillsModificationService;
    private final ModificationService<String> softSkillsModificationService;

    public SkillsModificationController(@Qualifier("hardSkillsModificationService") ModificationService<String> hardSkillsModificationService,
                                        @Qualifier("softSkillsModificationService") ModificationService<String> softSkillsModificationService) {
        this.hardSkillsModificationService = hardSkillsModificationService;
        this.softSkillsModificationService = softSkillsModificationService;
    }

    @PostMapping("/users/{userId}/skills")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId)")
    public long addSkills(@RequestBody Map<String, String> skills, @PathVariable("userId") long userId) {
        CompletableFuture<Void> asyncHardSkills = CompletableFuture.runAsync(() -> hardSkillsModificationService.add(userId, skills.get("hardSkills")));
        softSkillsModificationService.add(userId, skills.get("softSkills"));
        asyncHardSkills.join();
        return userId;
    }

    @PutMapping("/users/{userId}/skills")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId)")
    public long updateSkills(@RequestBody Map<String, String> skills, @PathVariable("userId") long userId) {
        CompletableFuture<Void> asyncHardSkills = CompletableFuture.runAsync(() -> hardSkillsModificationService.updateByUserId(userId, skills.get("hardSkills")));
        softSkillsModificationService.updateByUserId(userId, skills.get("softSkills"));
        asyncHardSkills.join();
        return userId;
    }

}
