package com.djawnstj.wordle.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FeedbackGeneratorTest {

    @Test
    @DisplayName("정답과 제대로 비교하는지 확인")
    void generateFeedback() {
        // Given
        FeedbackGenerator generator = new FeedbackGenerator();
        Answer answer = new Answer("abcce");
        String guess = "jeaca";
        String expectedResult = "XYYGX";

        // When
        String result = generator.generateFeedback(guess, answer);

        // Then
        assertThat(result).isEqualTo(expectedResult);
    }

}
