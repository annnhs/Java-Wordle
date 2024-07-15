package com.djawnstj.wordle.domain;

import com.djawnstj.wordle.ui.UIRenderer;

import java.util.Scanner;

public class Wordle {

    private final WordDictionary dictionary;
    private final WordValidator validator;
    private final UIRenderer ui;
    private ValidWord answer;
    private final Feedback feedback;
    private final Scanner scanner = new Scanner(System.in);

    private int numOfTry = 0;
    private final int lastTry = 6;

    public Wordle() {
        this.dictionary = new WordDictionary();
        this.validator = new WordValidator(dictionary);
        this.feedback = new Feedback();
        this.ui = new UIRenderer();
    }

    private void initDictionary() {
        dictionary.initWords();
        answer = new ValidWord(dictionary.getTodayWord(), validator);
    }

    public void startWordle() {
        initDictionary();
        ui.showStartGame();
        scanInput();
    }

    private void scanInput() {
        ui.showInput();
        final String input = scanner.nextLine().toLowerCase().replace(" ", "");

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
        ValidWord guess = new ValidWord(input, validator);
        numOfTry += 1;
        feedback.generateFeedback(guess, answer);

        if (answer.isEqualTo(guess.getWord()) || numOfTry == lastTry) {
            finishWordle(feedback);
            return;
        }

        ui.showFeedback(feedback);
        scanInput();
    }

    private void finishWordle(final Feedback feedback) {
        ui.showGameOver(numOfTry, feedback);

        if (feedback.isWrongAnswer()) {
            ui.showAnswer(answer.getWord());
        }
    }

}
