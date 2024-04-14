package com.portfolio.features.cv_features.modification_cv.controllers;

import com.portfolio.features.cv_features.modification_cv.services.ModificationService;
import com.portfolio.models.cv_blocks.Experience;
import org.springframework.web.bind.annotation.*;


@RestController
public class ModificationCVController {
    private final ModificationService<Experience> modificationService;

    public ModificationCVController(ModificationService<Experience> modificationService) {
        this.modificationService = modificationService;
    }

//    @PutMapping("/users/{id}/cv")
//    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #id) " +
//            "OR hasAnyAuthority('DIRECTOR', 'MODIFICATION_ADMIN')")
//    public CurriculumVitae updateCVById(@RequestBody Map<String, String> data, @PathVariable("id") long id) {
//        data.put("userId", String.valueOf(id));
//        return modificationCV.updateCV(data);
//    }


}
