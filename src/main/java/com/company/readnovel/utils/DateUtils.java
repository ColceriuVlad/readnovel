package com.company.readnovel.utils;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtils {
    private DateTimeFormatter dateTimeFormatter;

    public DateUtils() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public String getCurrentDate() {
        var currentDate = LocalDateTime.now();
        var currentDateString = dateTimeFormatter.format(currentDate);
        return currentDateString;
    }
}
