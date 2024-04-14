package com.portfolio.features.cv_features.modification_cv.services.parsers;

import com.google.gson.JsonElement;
import com.portfolio.models.cv_blocks.Experience;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.portfolio.helpers.date_helpers.date_parsers.DateParser.parseDate;

@Component
public final class ExperienceParser implements JsonParser<Experience> {

    @Override
    public List<Experience> parse(Map<String, String> data) {
        final List<Experience> result = new ArrayList<>();
        for(JsonElement element : com.google.gson.JsonParser.parseString(data.get("dataExperience")).getAsJsonArray()) {
            result.add(parseJson(Long.parseLong(data.get("userId")), element));
        }
        return result;
    }

    private Experience parseJson(long userId, JsonElement element) {
        long expId = element.getAsJsonObject().getAsJsonPrimitive("expId") == null ? 0 : element.getAsJsonObject().getAsJsonPrimitive("expId").getAsLong();
        String title = element.getAsJsonObject().getAsJsonPrimitive("title").getAsString();
        String startJobText = element.getAsJsonObject().getAsJsonPrimitive("startJob").getAsString();
        String finishJobText = element.getAsJsonObject().getAsJsonPrimitive("finishJob").getAsString();
        String description = element.getAsJsonObject().getAsJsonPrimitive("description").getAsString();
        LocalDate startJob = parseDate(startJobText, "yyyy-MM-dd");
        LocalDate finishJob = parseDate(finishJobText, "yyyy-MM-dd");
        Experience exp = new Experience(userId, title, startJob, finishJob, description);
        exp.setExpId(expId);
        return exp;
    }
}
