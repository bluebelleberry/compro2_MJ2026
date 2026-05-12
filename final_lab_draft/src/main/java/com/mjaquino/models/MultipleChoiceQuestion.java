package com.mjaquino.models;

import java.util.ArrayList;

public class MultipleChoiceQuestion extends Question {
    // data fields
    private ArrayList<String> choices;
    private String answer;

    // constructor
    public MultipleChoiceQuestion(int questionId, String questionText, ArrayList<String> choices, String answer, int timeLimit) {

        super(questionId, questionText, "multiple_choice", timeLimit);

        this.choices = choices;
        this.answer = answer;
    }

    // display question and check answer
    @Override
    public void displayQuestion() {

        System.out.println("\n[Multiple Choice]");
        System.out.println(questionText);

        for (String choice : choices) {
            System.out.println(choice);
        }

        System.out.println("Time Limit: " + timeLimit + " seconds");
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.trim().equalsIgnoreCase(answer);
    }

    // getters
    public ArrayList<String> getChoices() {
        return choices;
    }
}
