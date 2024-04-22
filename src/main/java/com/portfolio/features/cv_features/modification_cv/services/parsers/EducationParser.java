package com.portfolio.features.cv_features.modification_cv.services.parsers;

import com.google.gson.JsonElement;
import com.portfolio.models.cv_blocks.Education;
import com.portfolio.models.cv_blocks.EducationType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.portfolio.helpers.date_helpers.date_parsers.DateParser.parseDate;

@Component
public class EducationParser implements DataParser<Education> {

    @Override
    public List<Education> parse(Map<String, String> data) {
        final List<Education> result = new ArrayList<>();
        for(JsonElement element : com.google.gson.JsonParser.parseString(data.get("dataEducation")).getAsJsonArray()) {
            result.add(parseJson(Long.parseLong(data.get("userId")), element));
        }
        return result;
    }

    private Education parseJson(long userId, JsonElement element) {
        EducationType type = EducationType.valueOf(getValue(element, "type"));
        String specialization = getValue(element, "specialization");
        String institution = getValue(element, "institution");
        String degree = getValue(element, "degree");
        String startEduText = getValue(element, "startEdu");
        String finishEduText = getValue(element, "finishEdu");

        String description = getValue(element, "description");
        LocalDate startEdu = parseDate(startEduText, "yyyy-MM-dd");
        LocalDate finishEdu = parseDate(finishEduText, "yyyy-MM-dd");
        Education edu = new Education(userId);
        edu.setType(type);
        edu.setSpecialization(specialization);
        edu.setNameInstitute(institution);
        edu.setDegree(degree);
        edu.setStartEducation(startEdu);
        edu.setFinishEducation(finishEdu);
        return edu;
    }

    private String getValue(JsonElement element, String key) {
        try {
            return element.getAsJsonObject().get(key).getAsString();
        } catch (NullPointerException ignored) {
            return "";
        }
    }
}
