package com.djawnstj.wordle.domain;

import com.djawnstj.wordle.ui.UIRenderer;

import java.util.ArrayList;
import java.util.Scanner;

public class Wordle {

    private final WordDictionary dictionary;
    private final WordValidator validator;
    private final UIRenderer ui;
    private ValidWord answer;
    private final Feedback feedback;
    private final ArrayList<String> feedbacks;
    private final Scanner scanner = new Scanner(System.in);

    private int numOfTry = 0;
    private final int lastTry = 6;

    public Wordle() {
        this.dictionary = new WordDictionary();
        this.validator = new WordValidator(dictionary);
        this.feedback = new Feedback();
        this.feedbacks = new ArrayList<>();
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
        String feedbackOfInput = feedback.generateFeedback(guess, answer);
        feedbacks.add(feedbackOfInput);

        if (feedback.isCorrectAnswer(feedbackOfInput) || numOfTry == lastTry) {
            finishWordle(feedbackOfInput);
            return;
        }

        ui.showFeedback(feedbacks);
        scanInput();
    }

    private void finishWordle(final String feedbackOfInput) {
        final String resultOfTry = numOfTry + "/" + lastTry;
        ui.showGameOver(resultOfTry, feedbacks);

        if (!feedback.isCorrectAnswer(feedbackOfInput)) {
            ui.showAnswer(answer.getWord());
        }
    }

}
