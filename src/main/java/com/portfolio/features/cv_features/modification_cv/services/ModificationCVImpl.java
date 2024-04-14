package com.portfolio.features.cv_features.modification_cv.services;

import com.portfolio.features.cv_features.modification_cv.services.parsers.JsonParser;
import com.portfolio.models.CurriculumVitae;
import com.portfolio.models.cv_blocks.Experience;
import org.springframework.stereotype.Service;

import java.util.Map;

public class ModificationCVImpl  {
    private final ModificationService<Experience> experienceModificationService;
    private final JsonParser<Experience> experienceJsonParser;

    public ModificationCVImpl(ModificationService<Experience> experienceModificationService, JsonParser<Experience> experienceJsonParser) {
        this.experienceModificationService = experienceModificationService;
        this.experienceJsonParser = experienceJsonParser;
    }


    public CurriculumVitae updateCV(Map<String, String> data) {
        for(Experience experience : experienceJsonParser.parse(data)) {
            if(experience.getExpId() == 0) {
                experienceModificationService.add(experience);
            } else {
                experienceModificationService.update(experience);
            }
        }
        return null;
    }


    public long deleteExperienceById(long id) {
        return experienceModificationService.removeById(id);
    }
}