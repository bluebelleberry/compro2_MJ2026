package com.mjaquino.models;

public class QuizResponse {
    // data fields
    private boolean processed;
    private boolean correct;
    private int points;
    private String message;

    // constructor
    public QuizResponse(boolean processed, boolean correct, int points, String message) {
        this.processed = processed;
        this.correct = correct;
        this.points = points;
        this.message = message;
    }

    public boolean isProcessed() {
        return processed;
    }

    public boolean isCorrect() {
        return correct;
    }

    public int getPoints() {
        return points;
    }

    public String getMessage() {
        return message;
    }
}