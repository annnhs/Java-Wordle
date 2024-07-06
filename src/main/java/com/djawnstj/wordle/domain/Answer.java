package com.djawnstj.wordle.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Answer {

    private final List<String> words = new ArrayList<>();
    private final String answer;

    public Answer() {
        this.answer = getAnswerOfToday();
    }

    private String getAnswerOfToday() {
        int diffOfDays = getDiffOfDays();
        String[] words = getWordsList();

        int index = diffOfDays % words.length;

        return words[index];
    }

    private int getDiffOfDays() {
        final int baseYear = 2021;
        final int baseMonth = 6;
        final int baseDayOfMonth = 19;

        LocalDate baseDate = LocalDate.of(baseYear, baseMonth, baseDayOfMonth);
        LocalDate currentDate = LocalDate.now();

        long diff = ChronoUnit.DAYS.between(baseDate, currentDate);
        if (diff > Integer.MAX_VALUE || diff < Integer.MIN_VALUE) {
            throw new ArithmeticException("The difference in number of days from the base date exceeds the range of int type.");
        }

        return (int) diff;
    }

    private String[] getWordsList() {
        final String answerFileName = "words.txt";
        ClassLoader classLoader = getClass().getClassLoader();

        try {
            InputStream inputStream = classLoader.getResourceAsStream(answerFileName);
            if (inputStream == null) {
                throw new AssertionError("inputStream is null. Cannot find: " + answerFileName);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return words.toArray(new String[0]);
    }

    public boolean isNotContainInWords(final String guess) {
        return !words.contains(guess);
    }

    public final String compareWords(final String guess) {
        final char GREEN = 'G';
        final char YELLOW = 'Y';
        final char GREY = 'X';
        char[] result = new char[guess.length()];
        boolean[] answerUsed = new boolean[answer.length()];

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                result[i] = GREEN;
                answerUsed[i] = true;
                continue;
            }
            result[i] = GREY;
        }

        Map<Character, Integer> frequencyMap = createFrequencyMap(answerUsed);

        for (int i = 0; i < guess.length(); i++) {
            if (result[i] == GREEN) {
                continue;
            }
            char currentChar = guess.charAt(i);
            if (frequencyMap.containsKey(currentChar) && frequencyMap.get(currentChar) > 0) {
                result[i] = YELLOW;
                frequencyMap.put(currentChar, frequencyMap.get(currentChar) - 1);
            }
        }

        return new String(result);
    }

    private Map<Character, Integer> createFrequencyMap(boolean[] answerUsed) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < answer.length(); i++) {
            if (!answerUsed[i]) {
                frequencyMap.put(answer.charAt(i), frequencyMap.getOrDefault(answer.charAt(i), 0) + 1);
            }
        }
        return frequencyMap;
    }

    public String getAnswer() {
        return answer;
    }

}
