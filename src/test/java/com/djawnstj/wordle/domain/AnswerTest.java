package com.djawnstj.wordle.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class AnswerTest {

    @Test
    @DisplayName("(현재 날짜 - 2021년 6월 19일) 구하기")
    public void getDiffOfDays() {
        // Given
        int baseYear = 2021;
        int baseMonth = 6;
        int baseDayOfMonth = 19;

        LocalDate baseDate = LocalDate.of(baseYear, baseMonth, baseDayOfMonth);
        LocalDate currentDate = LocalDate.now();

        // When
        long diff = ChronoUnit.DAYS.between(baseDate, currentDate);
        if (diff > Integer.MAX_VALUE || diff < Integer.MIN_VALUE) {
            throw new ArithmeticException("The difference in number of days from the base date exceeds the range of int type.");
        }

        // Then
        int diffOfDays = (int) diff;
    }


    @Test
    @DisplayName("words.txt 파일 읽어와서 리스트에 담기")
    public void getWordsList() {
        // Given
        String answerFileName = "words.txt";
        int sizeOfWords = 2309;
        ClassLoader classLoader = getClass().getClassLoader();
        List<String> words = new ArrayList<>();

        // When
        try {
            InputStream inputStream = classLoader.getResourceAsStream(answerFileName);
            if (inputStream == null) {
                throw new AssertionError("inputStream is null.");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Then
        assertThat(words.size()).isEqualTo(sizeOfWords);
    }

    @Test
    @DisplayName("정답과 제대로 비교하는지 확인")
    void compareWords() {
        // Given
        String answer = "abcce";
        String guess = "jeaca";
        String resultString = "XYYGX";

        // When
        char[] result = new char[guess.length()];
        boolean[] answerUsed = new boolean[answer.length()];

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                result[i] = 'G';
                answerUsed[i] = true;
                continue;
            }
            result[i] = 'X';
        }

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < answer.length(); i++) {
            if (!answerUsed[i]) {
                frequencyMap.put(answer.charAt(i), frequencyMap.getOrDefault(answer.charAt(i), 0) + 1);
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (result[i] == 'G') {
                continue;
            }
            char currentChar = guess.charAt(i);
            if (frequencyMap.containsKey(currentChar) && frequencyMap.get(currentChar) > 0) {
                result[i] = 'Y';
                frequencyMap.put(currentChar, frequencyMap.get(currentChar) - 1);
            }
        }

        // Then
        assertThat(new String(result)).isEqualTo(resultString);
    }

}
