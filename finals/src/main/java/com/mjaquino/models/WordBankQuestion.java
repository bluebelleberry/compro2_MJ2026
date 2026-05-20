package com.mjaquino.models;

import java.util.List;

public class WordBankQuestion extends Question {

    private List<String> choices;
    private String answer;

    public WordBankQuestion() {
    }

    public WordBankQuestion(
            String question,
            List<String> choices,
            String answer,
            int points) {
        super(QuestionType.WORD_BANK, question, points);
        this.choices = choices;
        this.answer = answer;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean checkAnswer(Object userAnswer) {

        if (userAnswer == null) {
            return false;
        }

        String input = userAnswer.toString().trim();

        if (input.isEmpty()) {
            return false;
        }

        return answer.trim().equalsIgnoreCase(input);
    }

    @Override
    public int getScore(Object userAnswer) {
        return checkAnswer(userAnswer) ? getPoints() : 0;
    }

    @Override
    public void displayQuestion() {
        System.out.println(getQuestion());
        System.out.println("Word Bank:");

        for (String choice : choices) {
            System.out.print("[ " + choice + " ] ");
        }

        System.out.println();
    }

    @Override
    public Object getCorrectAnswer() {
        return answer;
    }
}