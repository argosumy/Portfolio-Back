package com.portfolio.features.cv_features.modification_cv.controllers;

import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import com.portfolio.features.cv_features.modification_cv.services.SelectService;
import com.portfolio.features.cv_features.modification_cv.services.parsers.DataParser;
import com.portfolio.models.cv_blocks.Education;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EducationModificationController {
    private final ModificationService<Education> modificationEducation;
    private final SelectService<Education> selectService;
    private final DataParser<Education> educationDataParser;

    public EducationModificationController(ModificationService<Education> modificationEducation,
                                           SelectService<Education> selectService,
                                           DataParser<Education> educationDataParser) {
        this.modificationEducation = modificationEducation;
        this.selectService = selectService;
        this.educationDataParser = educationDataParser;
    }

    @GetMapping("/users/{userId}/edu")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId) " +
            "OR hasAnyAuthority('MODIFICATION_CV')")
    public List<Education> getEducationByUserId(@PathVariable("userId") long userId) {
        return selectService.selectAllElementsByUserId(userId);
    }

    @PostMapping("/users/{userId}/edu")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId)")
    public String addNewEducation(@RequestBody Map<String, String> edu, @PathVariable("userId") long userId) {
        edu.put("userId", String.valueOf(userId));
        modificationEducation.addAll(userId, educationDataParser.parse(edu));
        return "OK";
    }

    @DeleteMapping ("/users/{userId}/edu/{id}")
    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #userId)")
    public long deleteEducationById(@PathVariable("userId") long userId, @PathVariable("id") long eduId) {
        return modificationEducation.removeById(eduId);
    }

}