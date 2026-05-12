package com.mjaquino.models;

public class IdentificationQuestion extends Question {
    // data fields
    private String answer;

    // constructor
    public IdentificationQuestion(int questionId, String questionText, String answer, int timeLimit) {

        super(questionId, questionText, "identification", timeLimit);

        this.answer = answer;
    }

    // getters
    public String getAnswer() {
        return answer;
    }

    // display question and check answer
    @Override
    public void displayQuestion() {

        System.out.println("\n[Identification]");
        System.out.println(questionText);
        System.out.println("Time Limit: " + timeLimit + " seconds");
    }

    @Override
    public boolean checkAnswer(String userAnswer) {

        return userAnswer.trim().equalsIgnoreCase(answer);
    }
}