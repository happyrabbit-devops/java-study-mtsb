package org.example.utils;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class DateUtils {

    private DateUtils() {
        //
    }

    public static final LocalDate START_DATE = LocalDate.of(2022, 1, 1);
    public static final LocalDate END_DATE = LocalDate.of(2022, 12, 31);

    public static LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        long startDay = startDate.toEpochDay();
        long endDay = endDate.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startDay, endDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static LocalDate getRandomBirthDate() {
        return generateRandomDate(START_DATE, END_DATE);
    }

}
