package com.mjaquino.models;

import java.util.List;

public class ExamResult {
    // data fields 
    private String studentId;
    private String fullName;
    private String timeIn;
    private String timeOut;
    private String quizDate;
    private int score;
    private int totalPoints;
    private int correctCount;
    private int incorrectCount;
    private int ranking;
    private int passingPercentage;
    private List<AnswerReview> reviews;
    // empty constructor
    public ExamResult() {
    }
    // constructor
    public ExamResult(
            String studentId,
            String fullName,
            String timeIn,
            String timeOut,
            String quizDate,
            int score,
            int totalPoints,
            int correctCount,
            int incorrectCount,
            int ranking,
            int passingPercentage,
            List<AnswerReview> reviews) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.quizDate = quizDate;
        this.score = score;
        this.totalPoints = totalPoints;
        this.correctCount = correctCount;
        this.incorrectCount = incorrectCount;
        this.ranking = ranking;
        this.passingPercentage = passingPercentage;
        this.reviews = reviews;
    }
    // getters and setters
    public String getStudentId() {
        return studentId;
    }

    public int getScore() {
        return score;
    }

    public double getPercentage() {
        if (totalPoints == 0) {
            return 0;
        }
        return (score * 100.0) / totalPoints;
    }

    public String getStatus() {
        return getPercentage() >= passingPercentage ? "PASSED" : "FAILED";
    }

    public String getFullName() {
        return fullName;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public List<AnswerReview> getReviews() {
        return reviews;
    }

    public int getRanking() {
        return ranking;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId
                + "\nName: " + fullName
                + "\nDate of Quiz: " + quizDate
                + "\nTime In: " + timeIn
                + "\nTime Out: " + timeOut
                + "\nScore: " + score + "/" + totalPoints
                + "\nCorrect: " + correctCount
                + "\nIncorrect: " + incorrectCount
                + "\nPercentage: " + String.format("%.2f", getPercentage()) + "%"
                + "\nPassing Percentage: " + passingPercentage + "%"
                + "\nStatus: " + getStatus()
                + "\nRanking: " + ranking;
    }
}