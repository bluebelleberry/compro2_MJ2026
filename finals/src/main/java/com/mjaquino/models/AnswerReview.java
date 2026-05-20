package com.mjaquino.models;

public class AnswerReview {
    // data fields
    private String question;
    private Object submittedAnswer;
    private Object correctAnswer;
    private int scoreEarned;
    private int points;

    // empty constructor 
    public AnswerReview() {
    }

    // constructor
    public AnswerReview(
            String question,
            Object submittedAnswer,
            Object correctAnswer,
            int scoreEarned,
            int points
    ) {
        this.question = question;
        this.submittedAnswer = submittedAnswer;
        this.correctAnswer = correctAnswer;
        this.scoreEarned = scoreEarned;
        this.points = points;
    }

    // getters
    public String getQuestion() {
        return question;
    }

    public Object getSubmittedAnswer() {
        return submittedAnswer;
    }

    public Object getCorrectAnswer() {
        return correctAnswer;
    }

    public int getScoreEarned() {
        return scoreEarned;
    }

    public int getPoints() {
        return points;
    }
}