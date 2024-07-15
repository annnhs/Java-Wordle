package com.djawnstj.wordle.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Feedback {

    private final ArrayList<String> feedbacks;
    private final FeedbackGenerator generator;

    public Feedback() {
        this.feedbacks = new ArrayList<>();
        this.generator = new FeedbackGenerator();
    }

    public void generateFeedback(final ValidWord guess, final ValidWord answer) {
        final String feedback = generator.generateFeedback(guess, answer);
        feedbacks.add(feedback);
    }

    public boolean isWrongAnswer() {
        final String allGreenFeedback = "\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9";
        return !Objects.equals(feedbacks.getLast(), allGreenFeedback);
    }

    public List<String> getFeedbacks() {
        return feedbacks.stream().toList();
    }

}
