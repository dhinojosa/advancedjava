package com.vmware;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.*;

public class UsingTemporalQuery {
    public static void main(String[] args) {
        TemporalQuery<Long> daysBeforeChristmas = new TemporalQuery<Long>() {
            public int daysTilChristmas (int acc, Temporal temporal) {
                int month = temporal.get(ChronoField.MONTH_OF_YEAR);
                int day = temporal.get(ChronoField.DAY_OF_MONTH);
                int max = Month.of(month).maxLength();
                if (month == 12 && day <= 25) return acc + (25 - day);
                return daysTilChristmas(acc + (max - day + 1),
                        temporal.with(TemporalAdjusters.firstDayOfNextMonth()));
            }


            @Override
            public Long queryFrom(TemporalAccessor temporal) {
                LocalDate ld = LocalDate.from(temporal);
                long d = Period.between(ld,LocalDate.of(ld.getYear(), 12, 25)).get(ChronoUnit.DAYS);
                if (d >= 0) {
                    return d;
                }
                return Period.between(ld,LocalDate.of(ld.getYear() + 1, 12, 25)).get(ChronoUnit.DAYS);
            }
        };

        System.out.println(LocalDate.of(2013, 12, 26).query(daysBeforeChristmas)); //365
        System.out.println(LocalDate.of(2013, 12, 23).query(daysBeforeChristmas)); //2
        System.out.println(LocalDate.of(2013, 12, 25).query(daysBeforeChristmas)); //0
        System.out.println(ZonedDateTime.of(2013, 12, 1, 11, 0, 13, 938282,
                ZoneId.of("America/Los_Angeles"))
                .query(daysBeforeChristmas)); //24

        DateTimeFormatter dateFormatter = DateTimeFormatter.
                ofLocalizedDate(FormatStyle.MEDIUM);
        System.out.println(dateFormatter.parse("Jan 19, 2014", LocalDate::from));
    }
}
