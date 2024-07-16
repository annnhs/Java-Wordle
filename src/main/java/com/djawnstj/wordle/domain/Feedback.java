package com.djawnstj.wordle.domain;

import java.util.Objects;

public class Feedback {

    private final FeedbackGenerator generator;

    public Feedback() {
        this.generator = new FeedbackGenerator();
    }

    public String generateFeedback(final ValidWord guess, final ValidWord answer) {
        return generator.generateFeedback(guess, answer);
    }

    public boolean isCorrectAnswer(final String feedback) {
        final String allGreenFeedback = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9";
        return Objects.equals(feedback, allGreenFeedback);
    }

}
