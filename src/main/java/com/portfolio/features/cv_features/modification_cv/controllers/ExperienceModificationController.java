package com.portfolio.features.cv_features.modification_cv.controllers;

import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import com.portfolio.features.cv_features.modification_cv.services.parsers.DataParser;
import com.portfolio.models.cv_blocks.Experience;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ExperienceModificationController {
    private final ModificationService<Experience> modificationExperience;
    private final DataParser<Experience> experienceDataParser;

    public ExperienceModificationController(ModificationService<Experience> modificationExperience,
                                            DataParser<Experience> experienceDataParser) {
        this.modificationExperience = modificationExperience;
        this.experienceDataParser = experienceDataParser;
    }

    @PostMapping("/users/{userId}/exp")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId) " +
            "OR hasAnyAuthority('DIRECTOR', 'MODIFICATION_ADMIN')")
    public List<Experience> addNewExperience(@RequestBody Map<String, String> exp, @PathVariable("userId") long userId) {
        exp.put("userId", String.valueOf(userId));
        modificationExperience.addAll(userId, experienceDataParser.parse(exp));
        return null;
    }

    @DeleteMapping("/users/{userId}/exp/{id}")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId) " +
            "OR hasAnyAuthority('DIRECTOR', 'MODIFICATION_ADMIN')")
    public long deleteExperienceById(@PathVariable("id") long id, @PathVariable("userId") long userId) {
        return modificationExperience.removeById(id);
    }
}
