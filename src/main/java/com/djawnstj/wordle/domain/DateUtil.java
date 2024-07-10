package com.djawnstj.wordle.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static int getDiffBetweenDays(final LocalDate startDate, final LocalDate endDate) {
        long diff = ChronoUnit.DAYS.between(startDate, endDate);
        if (diff > Integer.MAX_VALUE || diff < Integer.MIN_VALUE) {
            throw new ArithmeticException("The difference in number of days from the base date exceeds the range of int type.");
        }

        return (int) diff;
    }

}
