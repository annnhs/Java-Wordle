package com.djawnstj.wordle.domain;

public class WordValidator {

    private final WordDictionary wordDictionary;

    public WordValidator(final WordDictionary wordDictionary) {
        this.wordDictionary = wordDictionary;
    }

    public boolean isNotValidLength(final String guess) {
        final int length = 5;
        return guess.length() != length;
    }

    public boolean isNotInDictionary(final String guess) {
        return wordDictionary.isNotContainInWords(guess);
    }

}
