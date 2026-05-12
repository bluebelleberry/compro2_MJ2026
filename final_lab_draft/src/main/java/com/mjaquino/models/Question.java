package com.mjaquino.models;

public abstract class Question {
    // data feilds
    protected int questionId;
    protected String questionText;
    protected String type;
    protected int timeLimit;

    // constructor
    public Question(int questionId, String questionText, String type, int timeLimit) {

        this.questionId = questionId;
        this.questionText = questionText;
        this.type = type;
        this.timeLimit = timeLimit;
    }

    // getters
    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getType() {
        return type;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    // abstract methods
    public abstract void displayQuestion();

    public abstract boolean checkAnswer(String userAnswer);
}