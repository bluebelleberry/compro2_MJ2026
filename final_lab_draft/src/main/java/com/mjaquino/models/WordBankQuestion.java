package com.mjaquino.models;

import java.util.ArrayList;

public class WordBankQuestion extends Question {

    private ArrayList<String> wordBankChoices;
    private int answerIndex;

    public WordBankQuestion(String questionText,
                            ArrayList<String> wordBankChoices,
                            int answerIndex,
                            int timeLimit) {

        super(questionText, "word_bank", timeLimit);

        this.wordBankChoices = wordBankChoices;
        this.answerIndex = answerIndex;
    }

    @Override
    public void displayQuestion() {

        System.out.println("\n[Word Bank]");
        System.out.println(questionText);

        System.out.println("\nWord Bank:");

        for (String choice : wordBankChoices) {
            System.out.println("- " + choice);
        }

        System.out.println("Time Limit: " + timeLimit + " seconds");
    }

    @Override
    public boolean checkAnswer(String userAnswer) {

        String correctAnswer =
                wordBankChoices.get(answerIndex);

        return userAnswer.trim()
                .equalsIgnoreCase(correctAnswer);
    }

    public ArrayList<String> getWordBankChoices() {
        return wordBankChoices;
    }
}