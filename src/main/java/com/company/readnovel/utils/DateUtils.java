package com.company.readnovel.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateUtils {
    private DateTimeFormatter dateTimeFormatter;
    private Long currentMillis;

    public DateUtils() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        currentMillis = System.currentTimeMillis();
    }

    public String getCurrentDateString() {
        var currentDate = LocalDateTime.now();
        var currentDateString = dateTimeFormatter.format(currentDate);
        return currentDateString;
    }

    public Date getCurrentDate() {
        var date = new Date();
        date.setTime(currentMillis);
        return date;
    }

    public Date getCurrentIncreasedDate() {
        var date = new Date();
        var extraMillis = TimeUnit.HOURS.toMillis(24);
        var millis = currentMillis + extraMillis;
        date.setTime(millis);
        return date;
    }
}
