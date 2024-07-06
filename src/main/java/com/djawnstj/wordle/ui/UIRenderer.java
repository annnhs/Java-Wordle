package com.djawnstj.wordle.ui;

import java.util.ArrayList;

public class UIRenderer {

    private final ArrayList<String> results = new ArrayList<>();

    public void showStartGame() {
        printTitle();
        System.out.println("WORDLE을 6번 만에 맞춰 보세요.");
        System.out.println("시도의 결과는 타일의 색 변화로 나타납니다.");
    }

    public void showInput() {
        System.out.println("정답을 입력해 주세요.");
    }

    public void showIsNot5Words() {
        System.out.println("정답을 알파벳 5글자인 영단어로 입력해 주세요.");
    }

    public void showNotInWords() {
        System.out.println("단어 목록에 없는 단어입니다. 다시 입력해주세요.");
    }

    public void showGameOver(final int numOfTry, final String result) {
        System.out.println(numOfTry + "/6");
        System.out.println();
        showResult(result);
    }

    public void showResult(final String result) {
        addToResults(result);

        for (final String res : results) {
            System.out.println(res);
        }
    }

    private void addToResults(final String guess) {
        StringBuilder output = new StringBuilder();
        for (final char c : guess.toCharArray()) {
            if (c == 'G') {
                String GREEN = "\uD83D\uDFE9";
                output.append(GREEN);
            } else if (c == 'Y') {
                String YELLOW = "\uD83D\uDFE8";
                output.append(YELLOW);
            } else if (c == 'X') {
                String GREY = "⬜";
                output.append(GREY);
            }
        }

        results.add(output.toString());
    }

    public void showAnswer(final String answer) {
        System.out.println("정답은 " + answer + " 입니다.");
    }

    private void printTitle() {
        String[] W = {
                "  *       *",
                "  *       *",
                "  *   *   *",
                "  *  * *  *",
                "  * *   * *",
                "  *       *"
        };

        String[] O = {
                "    ****   ",
                "   *    *  ",
                "  *      * ",
                "  *      * ",
                "   *    *  ",
                "    ****   "
        };

        String[] R = {
                "  *****    ",
                " *     *   ",
                " *     *   ",
                "  *****    ",
                " *    *    ",
                " *     *   "
        };

        String[] D = {
                " *****     ",
                " *    *    ",
                " *     *   ",
                " *     *   ",
                " *    *    ",
                " *****     "
        };

        String[] L = {
                " *         ",
                " *         ",
                " *         ",
                " *         ",
                " *         ",
                " ******    "
        };

        String[] E = {
                " ******    ",
                " *         ",
                " *         ",
                " ******    ",
                " *         ",
                " ******    "
        };

        String[][] letters = {W, O, R, D, L, E};

        System.out.println("=========================================================================");
        for (int i = 0; i < 6; i++) {
            for (String[] letter : letters) {
                System.out.print(letter[i] + "  ");
            }
            System.out.println();
        }
        System.out.println("=========================================================================");
    }

}
