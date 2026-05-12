package com.mjaquino.models;

public class QuizSession {

    private Student student;
    private Exam exam;
    private int currentScore;
    private int currentQuestionIndex;
    private boolean active;

    public QuizSession(Student student, Exam exam) {
        this.student = student;
        this.exam = exam;
        this.currentScore = 0;
        this.currentQuestionIndex = 0;
        this.active = true;
    }

    public void incrementScore(int points) {
        currentScore += points;
    }

    public void nextQuestion() {
        currentQuestionIndex++;
    }

    public boolean isActive() {
        return active;
    }

    public void endSession() {
        active = false;
        student.setSubmitted(true);
    }

    public int getCurrentScore() {
        return currentScore;
    }
}
