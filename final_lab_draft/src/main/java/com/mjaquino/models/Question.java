package com.mjaquino.models;

public abstract class Question {
    // data feilds
    protected String questionText;
    protected String type;
    protected int timeLimit;

    // constructor
    public Question(String questionText,
                    String type,
                    int timeLimit) {

        this.questionText = questionText;
        this.type = type;
        this.timeLimit = timeLimit;
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

    public abstract void displayQuestion();

    public abstract boolean checkAnswer(String userAnswer);
}