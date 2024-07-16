package com.djawnstj.wordle.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FeedbackTest {

    private final WordDictionary dictionary = new WordDictionary();
    private final WordValidator validator = new WordValidator(dictionary);
    private final Feedback feedback = new Feedback();

    private final String GREEN = "\uD83D\uDFE9";
    private final String YELLOW = "\uD83D\uDFE8";
    private final String GRAY = "⬜";

    @Test
    @DisplayName("정답과 제대로 비교하는지 확인")
    void generateFeedback() {
        // Given
        dictionary.initWords();
        final ValidWord answer = new ValidWord("whelp", validator);
        final ValidWord guess = new ValidWord("jelly", validator);
        final String expectedResult = GRAY + YELLOW + GRAY + GREEN + GRAY;

        // When
        feedback.generateFeedback(guess, answer);
        final String result = feedback.getFeedbacks().getLast();

        // Then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("플레이어가 정답을 맞췄는지 체크")
    void isWrongAnswer() {
        // Given
        dictionary.initWords();
        final ValidWord answer = new ValidWord("whelp", validator);
        final ValidWord guess = new ValidWord("jelly", validator);

        // When
        feedback.generateFeedback(guess, answer);

        // Then
        assertThat(feedback.isWrongAnswer()).isTrue();
    }

}
