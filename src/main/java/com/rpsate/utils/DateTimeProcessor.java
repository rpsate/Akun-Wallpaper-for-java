package com.rpsate.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeProcessor {
    private final static ZoneId systemZonId = ZoneId.systemDefault();

    /**
     * LocalDateTime to Date
     * @param localDateTime
     * @return
     */
    public static Date local2date(LocalDateTime localDateTime) {
        //Instant is a time object accurate to nanoseconds
        Instant instant = localDateTime.atZone(systemZonId).toInstant();
        return Date.from(instant);
    }

    /**
     * Date to LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime date2local(Date date) {
        //Instant is a time object accurate to nanoseconds
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, systemZonId);
    }
}
