package com.djawnstj.wordle;

import com.djawnstj.wordle.domain.Answer;
import com.djawnstj.wordle.ui.UIRenderer;

import java.util.Objects;
import java.util.Scanner;

public class Wordle {

    private final Answer answer;
    private final UIRenderer renderer;
    private int numOfTry = 0;
    private final Scanner scanner = new Scanner(System.in);

    public Wordle() {
        this.answer = new Answer();
        this.renderer = new UIRenderer();
    }

    public void startWordle() {
        renderer.showStartGame();
        getInput();
    }

    private void getInput() {
        renderer.showInput();
        final String input = scanner.next();

        if (input.length() != 5) {
            renderer.showIsNot5Words();
            getInput();
            return;
        }

        if (answer.isNotContainInWords(input)) {
            renderer.showNotInWords();
            getInput();
            return;
        }

        checkInput(input);
    }

    private void checkInput(final String input) {
        numOfTry += 1;
        final String result = answer.compareWords(input);

        if (answer.getAnswer().equals(input) || numOfTry == 6) {
            finishWordle(result);
            return;
        }

        renderer.showResult(result);
        getInput();
    }

    private void finishWordle(final String result) {
        renderer.showGameOver(numOfTry, result);
        if (!Objects.equals(result, "GGGGG")) {
            renderer.showAnswer(answer.getAnswer());
        }
    }

}
