package com.mjaquino.models;

import java.time.LocalDateTime;

public class QuizSession {
    // data fields
    private Student student;
    private boolean active;
    private boolean submitted;
    private LocalDateTime startedAt;
    private LocalDateTime submittedAt;
    // empty constructor
    public QuizSession() {
    }
    // constructor
    public QuizSession(Student student) {
        this.student = student;
        this.active = true;
        this.submitted = false;
        this.startedAt = LocalDateTime.now();
    }
    // getters and setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
    // shows what time it was submmitted
    public void submit() {
        this.submitted = true;
        this.active = false;
        this.submittedAt = LocalDateTime.now();

        if(student != null) {
            student.setSubmitted(true);
        }
    }

    public void endSession() {
        this.active = false;
    }

    @Override
    public String toString() {
        return student.toString()
                + " | Active: " + active
                + " | Submitted: " + submitted;
    }
}