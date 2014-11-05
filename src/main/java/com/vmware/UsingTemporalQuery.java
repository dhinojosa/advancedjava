package com.vmware;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

public class UsingTemporalQuery {
    public static void main(String[] args) {
        TemporalQuery<Long> daysBeforeChristmas = new TemporalQuery<Long>() {
            @Override
            public Long queryFrom(TemporalAccessor temporal) {
                LocalDate ld = LocalDate.from(temporal);
                long d = ChronoUnit.DAYS.between(ld, LocalDate.of(ld.getYear(), 12, 25));
                if (d >= 0) {
                    return d;
                }
                return ChronoUnit.DAYS.between(ld, LocalDate.of(ld.getYear() + 1, 12, 25));
            }
        };

        System.out.println(LocalDate.of(2013, 12, 26).query(daysBeforeChristmas)); //364
        System.out.println(LocalDate.of(2015, 12, 26).query(daysBeforeChristmas)); //365
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
