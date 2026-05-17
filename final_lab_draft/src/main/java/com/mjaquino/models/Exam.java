package com.mjaquino.models;

import java.util.List;

public class Exam {
    // data fields
    private String title;
    private String password;
    private int durationMinutes;
    private int maxStudents;
    private int passingPercentage;
    private List<Question> questions;

    // empty constructor
    public Exam() {
    }

    // constructor
    public Exam(
            String title,
            String password,
            int durationMinutes,
            int maxStudents,
            int passingPercentage,
            List<Question> questions
    ) {
        this.title = title;
        this.password = password;
        this.durationMinutes = durationMinutes;
        this.maxStudents = maxStudents;
        this.passingPercentage = passingPercentage;
        this.questions = questions;
    }

    // getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public int getPassingPercentage() {
        return passingPercentage;
    }

    public void setPassingPercentage(int passingPercentage) {
        this.passingPercentage = passingPercentage;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    // computing total points
    public int getTotalPoints() {
        int total = 0;
        for(Question question : questions) {
            total += question.getPoints();
        }
        return total;
    }

    @Override
    public String toString() {

        return "Exam: " + title

                + "\nDuration: "
                + durationMinutes + " minutes"

                + "\nTotal Questions: "
                + questions.size()

                + "\nTotal Points: "
                + getTotalPoints()

                + "\nPassing Percentage: "
                + passingPercentage + "%"

                + "\nMaximum Students: "
                + maxStudents;
    }
}