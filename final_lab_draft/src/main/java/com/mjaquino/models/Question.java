package com.mjaquino.models;

public abstract class Question {
    // data fields
    protected QuestionType type;
    protected String question;
    protected int points;

    // empty constructor
    public Question() {
    }

    // constructor
    public Question(QuestionType type, String question, int points) {
        this.type = type;
        this.question = question;
        this.points = points;
    }

    // getters
    public QuestionType getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public int getPoints() {
        return points;
    }

    // setters
    public void setType(QuestionType type) {
        this.type = type;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // abstract methods

    // checking student's answer is correct
    public abstract boolean checkAnswer(Object userAnswer);
    // display questions
    public abstract void displayQuestion();
    // get the correct answer
    public abstract Object getCorrectAnswer();
    // get the score
    public abstract int getScore(Object userAnswer);
}