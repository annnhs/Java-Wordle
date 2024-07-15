package com.djawnstj.wordle.domain;

import java.util.HashMap;
import java.util.Map;

public class FeedbackGenerator {
    private final char CORRECT = 'G';
    private final char EXIST = 'Y';
    private final char WRONG = 'X';

    public String generateFeedback(final ValidWord guess, final ValidWord answer) {
        final boolean[] answerUsed = new boolean[answer.length()];

        final char[] greenFeedback = checkGreenFeedback(guess, answer, answerUsed);

        final Map<Character, Integer> frequencyMap = createFrequencyMap(answerUsed, answer);

        final char[] feedback = checkYellowFeedback(guess, greenFeedback, frequencyMap);

        return makeFeedbackVisual(feedback);
    }

    private char[] checkGreenFeedback(final ValidWord guess, final ValidWord answer, final boolean[] answerUsed) {
        int guessSize = guess.length();
        final char[] feedback = new char[guessSize];

        for (int i = 0; i < guessSize; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                feedback[i] = CORRECT;
                answerUsed[i] = true;
                continue;
            }
            feedback[i] = WRONG;
        }
        return feedback;
    }

    private Map<Character, Integer> createFrequencyMap(final boolean[] answerUsed, final ValidWord answer) {
        int answerSize = answer.length();
        final Map<Character, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < answerSize; i++) {
            if (!answerUsed[i]) {
                frequencyMap.put(answer.charAt(i), frequencyMap.getOrDefault(answer.charAt(i), 0) + 1);
            }
        }
        return frequencyMap;
    }

    private char[] checkYellowFeedback(final ValidWord guess, final char[] feedback, final Map<Character, Integer> frequencyMap) {
        int guessSize = guess.length();
        for (int i = 0; i < guessSize; i++) {
            if (feedback[i] == CORRECT) {
                continue;
            }
            final char currentChar = guess.charAt(i);
            if (frequencyMap.containsKey(currentChar) && frequencyMap.get(currentChar) > 0) {
                feedback[i] = EXIST;
                frequencyMap.put(currentChar, frequencyMap.get(currentChar) - 1);
            }
        }
        return feedback;
    }

    private String makeFeedbackVisual(final char[] feedback) {
        StringBuilder output = new StringBuilder();
        for (final char c : feedback) {
            if (c == CORRECT) {
                String GREEN = "\uD83D\uDFE9";
                output.append(GREEN);
            } else if (c == EXIST) {
                String YELLOW = "\uD83D\uDFE8";
                output.append(YELLOW);
            } else if (c == WRONG) {
                String GRAY = "⬜";
                output.append(GRAY);
            }
        }

        return output.toString();
    }

}
