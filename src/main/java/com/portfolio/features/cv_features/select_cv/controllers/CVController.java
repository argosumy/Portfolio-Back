package com.portfolio.features.cv_features.select_cv.controllers;

import com.portfolio.features.cv_features.select_cv.services.CVService;
import com.portfolio.models.CurriculumVitae;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
