package com.djawnstj.wordle.domain;

public class Answer {

    private final String answer;

    public Answer(final String word) {
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
