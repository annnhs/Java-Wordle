package com.djawnstj.wordle.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WordValidatorTest {

    private final WordDictionary dictionary = new WordDictionary();
    private final WordValidator validator = new WordValidator(dictionary);
    private final String guess1 = "cigar";
    private final String guess2 = "abcde";
    private final String guess3 = "abcdef";

    @Test
    @DisplayName("단어가 단어 목록에 있고 5글자인지 체크")
    void isValidWord() {
        // Given
        dictionary.initWords();

        // When
        final boolean isGuess1IsValid = validator.isValidWord(guess1);
        final boolean isGuess2IsValid = validator.isValidWord(guess2);
        final boolean isGuess3IsValid = validator.isValidWord(guess3);

        // Then
        assertThat(isGuess1IsValid).isTrue();
        assertThat(isGuess2IsValid).isFalse();
        assertThat(isGuess3IsValid).isFalse();
    }

    @Test
    @DisplayName("플레이어가 제출한 단어가 words.txt 에 포함된 단어인지 체크")
    void isMatchInWords() {
        // Given
        dictionary.initWords();

        // When
        final boolean isGuess1InDictionary = validator.isNotInDictionary(guess1);
        final boolean isGuess2InDictionary = validator.isNotInDictionary(guess2);

        // Then
        assertThat(isGuess1InDictionary).isFalse();
        assertThat(isGuess2InDictionary).isTrue();
    }

    @Test
    @DisplayName("플레이어가 제출한 단어가 5글자인지 체크")
    void isNotValidLength() {
        // Given
        dictionary.initWords();

        // When
        final boolean isGuess2FiveLetters = validator.isNotValidLength(guess2);
        final boolean isGuess3FiveLetters = validator.isNotValidLength(guess3);

        // Then
        assertThat(isGuess2FiveLetters).isFalse();
        assertThat(isGuess3FiveLetters).isTrue();
    }

}
