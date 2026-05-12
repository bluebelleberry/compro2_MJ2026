package com.mjaquino.models;

public class IdentificationQuestion extends Question {

    private String answer;

    public IdentificationQuestion(String questionText,
                                  String answer,
                                  int timeLimit) {

        super(questionText, "identification", timeLimit);

        this.answer = answer;
    }

    @Override
    public void displayQuestion() {

        System.out.println("\n[Identification]");
        System.out.println(questionText);
        System.out.println("Time Limit: " + timeLimit + " seconds");
    }

    @Override
    public boolean checkAnswer(String userAnswer) {

        return userAnswer.trim()
                .equalsIgnoreCase(answer);
    }
}