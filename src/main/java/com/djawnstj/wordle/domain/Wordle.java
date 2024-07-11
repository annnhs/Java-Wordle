package com.djawnstj.wordle.domain;

import com.djawnstj.wordle.ui.UIRenderer;

import java.util.Objects;
import java.util.Scanner;

public class Wordle {

    private final WordDictionary dictionary;
    private final WordValidator validator;
    private final FeedbackGenerator feedbackGenerator;
    private final UIRenderer ui;
    private int numOfTry = 0;
    private Answer answer;
    private final Scanner scanner = new Scanner(System.in);

    public Wordle() {
        this.dictionary = new WordDictionary();
        this.validator = new WordValidator(dictionary);
        this.feedbackGenerator = new FeedbackGenerator();
        this.ui = new UIRenderer();
    }

    private void initDictionary() {
        dictionary.initWords();
        answer = new Answer(dictionary.getTodayWord());
    }

    public void startWordle() {
        initDictionary();
        ui.showStartGame();
        scanInput();
    }

    private void scanInput() {
        ui.showInput();
        final String input = scanner.nextLine().toLowerCase();

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

        if (answer.isEqualTo(input) || numOfTry == 6) {
            finishWordle(feedback);
            return;
        }

        ui.showResult(feedback);
        scanInput();
    }

    private void finishWordle(final String feedback) {
        ui.showGameOver(numOfTry, feedback);

        if (isWrongAnswer(feedback)) {
            ui.showAnswer(answer.getAnswer());
        }
    }

    private boolean isWrongAnswer(final String feedback) {
        final String correctWord = "GGGGG";
        return !Objects.equals(feedback, correctWord);
    }

}
