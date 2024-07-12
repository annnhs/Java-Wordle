package com.djawnstj.wordle.domain;

public class WordValidator {

    private final WordDictionary wordDictionary;
    private final int validLength = 5;

    public WordValidator(final WordDictionary wordDictionary) {
        this.wordDictionary = wordDictionary;
    }

    public boolean isValidWord(final String word) {
        return word.length() == validLength && wordDictionary.anyMatchInWords(word);
    }

    public boolean isNotValidLength(final String guess) {
        return guess.length() != validLength;
    }

    public boolean isNotInDictionary(final String guess) {
        return wordDictionary.noneMatchInWords(guess);
    }

}
