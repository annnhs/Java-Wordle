package com.djawnstj.wordle.ui;

import com.djawnstj.wordle.domain.Feedback;

import java.util.List;

public class UIRenderer {

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

    public void showGameOver(final int numOfTry, final Feedback feedback) {
        final int lastTry = 6;
        System.out.println(numOfTry + "/" + lastTry);
        System.out.println();
        showFeedback(feedback);
    }

    public void showFeedback(final Feedback feedback) {
        final List<String> feedbacks = feedback.getFeedbacks();

        for (final String result : feedbacks) {
            System.out.println(result);
        }
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
