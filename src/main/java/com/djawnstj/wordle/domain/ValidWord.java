package com.djawnstj.wordle.domain;

public class ValidWord {

    private final String word;

    public ValidWord(final String word, final WordValidator validator) {
        if (!validator.isValidWord(word)) {
            throw new IllegalArgumentException("The word must be 5 letters long and in the dictionary.");
        }
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public char charAt(final int index) {
        return word.charAt(index);
    }

    public int length() {
        return word.length();
    }

}
