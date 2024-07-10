package com.djawnstj.wordle.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilTest {

    @Test
    @DisplayName("(현재 날짜 - 2021년 6월 19일) 구하기")
    public void getDiffOfDays() {
        // Given
        final int baseYear = 2021;
        final int baseMonth = 6;
        final int baseDayOfMonth = 19;

        final int todayYear = 2024;
        final int todayMonth = 7;
        final int todayOfMonth = 9;

        final int expectedDiff = 1116;
        final LocalDate baseDate = LocalDate.of(baseYear, baseMonth, baseDayOfMonth);
        final LocalDate today = LocalDate.of(todayYear, todayMonth, todayOfMonth);

        // When
        final int diffOfDays = DateUtil.getDiffBetweenDays(baseDate, today);

        // Then
        assertThat(expectedDiff).isEqualTo(diffOfDays);
    }

}
