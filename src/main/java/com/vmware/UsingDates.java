package com.vmware;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

import static java.time.temporal.ChronoUnit.*;

public class UsingDates {
    public static void main(String[] args) {
        Date date = new java.util.Date();
        System.out.println(date.getYear() + 1900);

        System.out.println(System.currentTimeMillis());
        System.out.println(Instant.now());

        Instant instant = Instant.now();
//        System.out.println(instant.getEpochSecond());
//        System.out.println(instant.getNano());
        System.out.println(instant);

        Instant newInstant = instant.plus(19, HOURS);
        System.out.println(instant);
        System.out.println(newInstant);
        System.out.println(Instant.now().get(ChronoField.MILLI_OF_SECOND));
        System.out.println(LocalDate.parse("2014-11-24")); //ISO-8601
        LocalTime.of(23, 11, 30);

        LocalDateTime standardTime6 = LocalDateTime.of(2014, 11, 2, 1, 30, 0, 0);

        long secs1 = standardTime6.atZone(ZoneId.of("America/New_York"))
                .withEarlierOffsetAtOverlap().toInstant().getEpochSecond();

        long secs2 = standardTime6.atZone(ZoneId.of("America/New_York"))
                .withLaterOffsetAtOverlap().toInstant().getEpochSecond();

        System.out.println(secs2 - secs1 == (3600));


        LocalDateTime daylightSavingTime1 = LocalDateTime.of(2014, 3, 9, 2, 0, 0, 0);
        LocalDateTime daylightSavingTime2 = LocalDateTime.of(2014, 3, 9, 3, 0, 0, 0);
        ZonedDateTime zonedDateTime1 = daylightSavingTime1.atZone(ZoneId.of("America/Denver"));
        ZonedDateTime zonedDateTime2 = daylightSavingTime2.atZone(ZoneId.of("America/Denver"));

        System.out.println(zonedDateTime2.toInstant().getEpochSecond() -
                zonedDateTime1.toInstant().getEpochSecond());

        LocalDateTime d1 = LocalDateTime.of(2014, 12, 9, 15, 0, 0, 0);
        LocalDateTime d2 = LocalDateTime.of(2014, 5, 9, 3, 0, 0, 0);

        System.out.println(Duration.between(d2, d1));
        LocalTime.of(12, 0, 0).with(t -> t.plus(4, MINUTES));

        DateTimeFormatter obscurePattern =
                DateTimeFormatter.ofPattern("MMMM dd, yyyy '(In Time Zone: 'VV')'");
        ZonedDateTime zonedNow = ZonedDateTime.now();

        System.out.println(obscurePattern.format(zonedNow));

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Paris"));

        DateTimeFormatter longDateTimeFormatter =
                DateTimeFormatter
                        .ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.FULL)
                        .withLocale(Locale.CHINA);
        longDateTimeFormatter.getLocale(); //fr
        System.out.println(longDateTimeFormatter.format(zonedDateTime));
    }
}
