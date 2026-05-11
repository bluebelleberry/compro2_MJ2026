package com.mjaquino.models;

public class Student {
    // data feilds
    private String studentId;
    private String name;
    private int score;

    

    // constructor
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.score = 0;
    }
    // getters
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    // setters
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }

    //display student info
    @Override
    public String toString() {
        return "Student ID: " + studentId +
               "\nName: " + name +
               "\nScore: " + score;
    }
}

