package com.mjaquino.models;

import java.util.ArrayList;

public class EnumerationQuestion extends Question {
    // data fields
    private ArrayList<String> answers;

    public EnumerationQuestion(int questionId, String questionText, ArrayList<String> answers, int timeLimit) {

        super(questionId, questionText, "enumeration", timeLimit);

        this.answers = answers;
    }

    // display question and check answer
    @Override
    public void displayQuestion() {

        System.out.println("\n[Enumeration]");
        System.out.println(questionText);
        System.out.println("Time Limit: " + timeLimit + " seconds");
    }

    @Override
    public boolean checkAnswer(String userAnswer) {

        for (String answer : answers) {
            if (answer.equalsIgnoreCase(userAnswer.trim())) {
                return true;
            }
        }
        return false;
    }

    // getters
    public ArrayList<String> getAnswers() {
        return answers;
    }
}