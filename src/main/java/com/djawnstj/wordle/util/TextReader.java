package com.djawnstj.wordle.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TextReader {

    private final List<String> words = new ArrayList<>();

    public String[] convertFileToList(final String fileName) throws IOException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final InputStream inputStream = classLoader.getResourceAsStream(fileName);
        checkFileName(fileName, inputStream);

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            addToWords(reader);
        } finally {
            inputStream.close();
        }

        return words.toArray(new String[0]);
    }

    private void checkFileName(final String fileName, final InputStream inputStream) {
        if (inputStream == null) {
            throw new AssertionError("inputStream is null. Cannot find: " + fileName);
        }
    }

    private void addToWords(final BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            words.add(line);
        }
    }

}
