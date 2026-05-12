package com.mjaquino.models;

public class Student {
    // data feilds
    private String studentId;
    private String fullName;
    private int score;
    private boolean submitted;

    // constructor 
    public Student(String studentId, String fullName) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.score = 0;
        this.submitted = false;
    }
    
    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public int getScore() {
        return score;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void addScore(int points) {
        score += points;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }
}
