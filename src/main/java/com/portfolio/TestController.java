package com.portfolio;

import com.portfolio.models.cv_blocks.Education;
import com.portfolio.models.cv_blocks.EducationComparator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class TestController {
    @GetMapping("test")
    public List<Education> getEdu() {
        List<Education> res = new ArrayList<>();
        res.add(getEducation(6, LocalDate.now().minusDays(7), null));
        res.add(getEducation(1, LocalDate.now().minusDays(7), LocalDate.now().minusDays(1)));
        res.add(getEducation(2, LocalDate.now().minusDays(3), LocalDate.now()));
        res.add(getEducation(3, LocalDate.now().minusDays(15), LocalDate.now().minusDays(10)));
        res.add(getEducation(5, LocalDate.now().minusDays(7), LocalDate.now().minusDays(1)));
        res.add(getEducation(8, LocalDate.now().minusDays(22), null));
        Comparator<Education> educationComparator = new EducationComparator();
//        Comparator<Education> nullCom = Comparator.nullsFirst(educationComparator);
        res.sort(educationComparator);
        return res;
    }

    private Education getEducation(long id, LocalDate start, LocalDate finish) {
        Education education = new Education(id);
        education.setStartEducation(start);
        education.setFinishEducation(finish);
        return education;
    }
}
