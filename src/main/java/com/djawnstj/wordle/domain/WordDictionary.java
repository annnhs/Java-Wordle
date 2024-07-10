package com.djawnstj.wordle.domain;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class WordDictionary {

    private final String[] words;

    public WordDictionary() {
        this.words = initWords();
    }

    private String[] initWords() {
        final String answerFileName = "words.txt";
        TextReader textReader = new TextReader();

        try {
            return textReader.convertFileToList(answerFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTodayWord() {
        final int baseYear = 2021;
        final int baseMonth = 6;
        final int baseDayOfMonth = 19;
        final LocalDate baseDate = LocalDate.of(baseYear, baseMonth, baseDayOfMonth);
        final LocalDate currentDate = LocalDate.now();

        final int diffOfDays = DateUtil.getDiffBetweenDays(baseDate, currentDate);

        final int index = diffOfDays % words.length;
        return this.words[index];
    }

    public boolean isNotContainInWords(final String guess) {
        return Arrays.stream(words).noneMatch(word -> word.equalsIgnoreCase(guess));
    }

}
