package com.djawnstj.wordle.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FeedbackGeneratorTest {

    @Test
    @DisplayName("정답과 제대로 비교하는지 확인")
    void generateFeedback() {
        // Given
        String GREEN = "\uD83D\uDFE9";
        String YELLOW = "\uD83D\uDFE8";
        String GRAY = "⬜";
        FeedbackGenerator generator = new FeedbackGenerator();
        WordDictionary dictionary = new WordDictionary();
        dictionary.initWords();
        WordValidator validator = new WordValidator(dictionary);
        ValidWord answer = new ValidWord("whelp", validator);
        ValidWord guess = new ValidWord("jelly", validator);
        String expectedResult = GRAY + YELLOW + GRAY + GREEN + GRAY;

        // When
        String result = generator.generateFeedback(guess, answer);

        // Then
        assertThat(result).isEqualTo(expectedResult);
    }

}
