package com.djawnstj.wordle;

import com.djawnstj.wordle.domain.FeedbackGenerator;
import com.djawnstj.wordle.domain.WordDictionary;
import com.djawnstj.wordle.domain.WordValidator;
import com.djawnstj.wordle.ui.UIRenderer;

import java.util.Objects;
import java.util.Scanner;

public class Wordle {

    private final WordDictionary dictionary;
    private final WordValidator validator;
    private final FeedbackGenerator feedbackGenerator;
    private final UIRenderer ui;
    private int numOfTry = 0;
    private String answer;
    private final Scanner scanner = new Scanner(System.in);

    public Wordle() {
        this.dictionary = new WordDictionary();
        this.validator = new WordValidator(dictionary);
        this.feedbackGenerator = new FeedbackGenerator();
        this.ui = new UIRenderer();
    }

    private void initDictionary() {
        dictionary.initWords();
        answer = dictionary.getTodayWord();
    }

    public void startWordle() {
        initDictionary();
        ui.showStartGame();
        scanInput();
    }

    private void scanInput() {
        ui.showInput();
        final String input = scanner.next().toLowerCase();

        if (isNotValidInput(input)) {
            scanInput();
            return;
        }

        getFeedback(input);
    }

    private boolean isNotValidInput(final String input) {
        if (validator.isNotValidLength(input)) {
            ui.showIsNot5Words();
            return true;
        }

        if (validator.isNotInDictionary(input)) {
            ui.showNotInWords();
            return true;
        }
        return false;
    }

    private void getFeedback(final String input) {
        numOfTry += 1;
        final String feedback = feedbackGenerator.generateFeedback(input, answer);

        if (answer.equals(input) || numOfTry == 6) {
            finishWordle(feedback);
            return;
        }

        ui.showResult(feedback);
        scanInput();
    }

    private void finishWordle(final String feedback) {
        final String allGreen = "GGGGG";
        ui.showGameOver(numOfTry, feedback);
        if (!Objects.equals(feedback, allGreen)) {
            ui.showAnswer(answer);
        }
    }

}
