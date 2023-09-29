package com.repill.was.global.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Optional;

public class TimeUtils {
    private TimeUtils() { }

    public static String convertToISO_8061(LocalDateTime zonedDateTime) {
        return Optional.ofNullable(zonedDateTime)
                .map(time ->
                        time.format(DateTimeFormatter.ISO_LOCAL_DATE)
                ).orElse(null);
    }
    public static String convertToServiceEventFormat(LocalDateTime zonedDateTime) {
        return Optional.ofNullable(zonedDateTime)
                .map(time ->
                        time.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
                ).orElse(null);
    }

    public static String convertToOpenApiFormat(ZonedDateTime zonedDateTime) {
        return Optional.ofNullable(zonedDateTime)
                .map(time ->
                        time.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                ).orElse(null);
    }

    public static String convertToDoznDt(ZonedDateTime zonedDateTime) {
        return Optional.ofNullable(zonedDateTime)
                .map(time ->
                        time.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                ).orElse(null);
    }
    public static ZonedDateTime fromOpenApiFormat(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return ZonedDateTime.parse(dateStr, formatter.withZone(ZoneId.of("Asia/Seoul")));
    }
    public static ZonedDateTime fromYYYYMMDDofSeoul(String dateStr) {
        DateTimeFormatter DATEFORMATTER = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
        return ZonedDateTime.parse(dateStr, DATEFORMATTER.withZone(ZoneId.of("Asia/Seoul")));
    }
    public static ZonedDateTime getSystemZonedDateTime(long millis) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }
    public static ZonedDateTime fromISO_8061(String dateStr) {
        return ZonedDateTime.parse(dateStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
