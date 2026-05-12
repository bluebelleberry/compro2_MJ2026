package com.mjaquino.models;

public class ExamResult {
    // data fields
    private Student student;
    private int score;
    private int totalItems;

    // constructor
    public ExamResult(Student student, int score, int totalItems) {

        this.student = student;
        this.score = score;
        this.totalItems = totalItems;
    }

    // display result
    public void displayResult() {
        System.out.println("\nRESULT");
        System.out.println("Student ID: "+ student.getStudentId());
        System.out.println("Full Name: " + student.getFullName());
        System.out.println("Score: " + score + "/" + totalItems);
    }
}
