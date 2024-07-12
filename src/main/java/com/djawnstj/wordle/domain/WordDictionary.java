package com.djawnstj.wordle.domain;

import com.djawnstj.wordle.util.DateUtil;
import com.djawnstj.wordle.util.TextReader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class WordDictionary {

    private String[] words;

    public void initWords() {
        final String answerFileName = "words.txt";
        TextReader textReader = new TextReader();

        try {
            words = textReader.convertFileToList(answerFileName);
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

    public boolean anyMatchInWords(final String word) {
        return Arrays.stream(words).anyMatch(wordInWords -> wordInWords.equalsIgnoreCase(word));
    }

    public boolean noneMatchInWords(final String word) {
        return Arrays.stream(words).noneMatch(wordInWords -> wordInWords.equalsIgnoreCase(word));
    }

}
