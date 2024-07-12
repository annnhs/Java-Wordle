package com.djawnstj.wordle.domain;

public class Answer {

    private final String answer;

    public Answer(final String word, final WordValidator validator) {
        if (!validator.isValidWord(word)) {
            throw new IllegalArgumentException("The word must be 5 letters long and in the dictionary.");
        }
        this.answer = word;
    }

    public boolean isEqualTo(final String guess) {
        return answer.equals(guess);
    }

    public String getAnswer() {
        return answer;
    }

    public char charAt(final int index) {
        return answer.charAt(index);
    }

    public int length() {
        return answer.length();
    }

}
