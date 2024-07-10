package com.djawnstj.wordle.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TextReader {

    public String[] convertFileToList(final String fileName) throws IOException {
        List<String> words = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new AssertionError("inputStream is null. Cannot find: " + fileName);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            words.add(line);
        }

        inputStream.close();
        reader.close();

        return words.toArray(new String[0]);
    }

}
