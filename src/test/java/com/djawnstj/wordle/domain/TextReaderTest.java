package com.djawnstj.wordle.domain;

import com.djawnstj.wordle.util.TextReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class TextReaderTest {

    @Test
    @DisplayName("words.txt 파일 읽어와서 리스트에 담기")
    void convertFileToList() {
        // Given
        String answerFileName = "words.txt";
        int sizeOfWords = 2309;
        TextReader textReader = new TextReader();
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

}
