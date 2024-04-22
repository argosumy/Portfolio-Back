package com.portfolio.helpers.date_helpers.date_parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {

    public static LocalDate parseDate(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date == null || date.isEmpty()
                ? null
                : LocalDate.parse(date, formatter);
    }


}
