package com.djawnstj.wordle.domain;

import java.util.HashMap;
import java.util.Map;

public class FeedbackGenerator {
    final char GREEN = 'G';
    final char YELLOW = 'Y';
    final char GREY = 'X';

    public String generateFeedback(final String guess, final String answer) {
        final boolean[] answerUsed = new boolean[answer.length()];

        final char[] greenFeedback = checkGreenFeedback(guess, answer, answerUsed);

        final Map<Character, Integer> frequencyMap = createFrequencyMap(answerUsed, answer);

        final char[] feedback = checkYellowFeedback(guess, greenFeedback, frequencyMap);

        return new String(feedback);
    }

    private char[] checkGreenFeedback(final String guess, final String answer, final boolean[] answerUsed) {
        final char[] feedback = new char[guess.length()];

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                feedback[i] = GREEN;
                answerUsed[i] = true;
                continue;
            }
            feedback[i] = GREY;
        }
        return feedback;
    }

    private Map<Character, Integer> createFrequencyMap(final boolean[] answerUsed, final String answer) {
        final Map<Character, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < answer.length(); i++) {
            if (!answerUsed[i]) {
                frequencyMap.put(answer.charAt(i), frequencyMap.getOrDefault(answer.charAt(i), 0) + 1);
            }
        }
        return frequencyMap;
    }

    private char[] checkYellowFeedback(final String guess, final char[] feedback, final Map<Character, Integer> frequencyMap) {
        for (int i = 0; i < guess.length(); i++) {
            if (feedback[i] == GREEN) {
                continue;
            }
            final char currentChar = guess.charAt(i);
            if (frequencyMap.containsKey(currentChar) && frequencyMap.get(currentChar) > 0) {
                feedback[i] = YELLOW;
                frequencyMap.put(currentChar, frequencyMap.get(currentChar) - 1);
            }
        }
        return feedback;
    }

}
