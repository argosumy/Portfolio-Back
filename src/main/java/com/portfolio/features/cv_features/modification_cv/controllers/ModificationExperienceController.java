package com.portfolio.features.cv_features.modification_cv.controllers;

import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import com.portfolio.features.cv_features.modification_cv.services.parsers.JsonParser;
import com.portfolio.models.cv_blocks.Experience;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ModificationExperienceController {
    private final ModificationService<Experience> modificationExperience;
    private final JsonParser<Experience> experienceJsonParser;

    public ModificationExperienceController(ModificationService<Experience> modificationExperience,
                                            JsonParser<Experience> experienceJsonParser) {
        this.modificationExperience = modificationExperience;
        this.experienceJsonParser = experienceJsonParser;
    }

    @PostMapping("/users/{userId}/exp")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId) " +
            "OR hasAnyAuthority('DIRECTOR', 'MODIFICATION_ADMIN')")
    public List<Experience> addNewExperience(@RequestBody Map<String, String> exp, @PathVariable("userId") long userId) {
        exp.put("userId", String.valueOf(userId));
        modificationExperience.addAll(experienceJsonParser.parse(exp));
        return null;
    }

    @DeleteMapping("/users/{userId}/exp/{id}")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId) " +
            "OR hasAnyAuthority('DIRECTOR', 'MODIFICATION_ADMIN')")
    public long deleteExperienceById(@PathVariable("id") long id, @PathVariable("userId") long userId) {
        return modificationExperience.removeById(id);
    }
}
