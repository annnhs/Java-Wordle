package com.djawnstj.wordle.domain;

import com.djawnstj.wordle.util.TextReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class WordDictionaryTest {

    @Test
    @DisplayName("words.txt 파일 읽어와서 리스트에 담기")
    void convertFileToList() {
        // Given
        final String answerFileName = "words.txt";
        final int sizeOfWords = 2309;
        final TextReader textReader = new TextReader();
        String[] words;

        // When
        try {
            words = textReader.convertFileToList(answerFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Then
        assertThat(words.length).isEqualTo(sizeOfWords);
    }

    @Test
    @DisplayName("존재하지 않는 파일은 예외")
    void cannotFindFileName() {
        // Given
        final String answerFileName = "abcd.txt";
        final TextReader textReader = new TextReader();

        // When Then
        assertThatThrownBy(() -> {
            try {
                String[] words = textReader.convertFileToList(answerFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).isInstanceOf(AssertionError.class)
                .hasMessage("inputStream is null. Cannot find: " + answerFileName);
    }

}
