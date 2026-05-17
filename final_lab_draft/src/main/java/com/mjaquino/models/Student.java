package com.mjaquino.models;

public class Student {
    // data fields
    private String studentId;
    private String firstName;
    private String lastName;
    private boolean submitted;

    // empty constructor
    public Student() {
    }

    // constructor
    public Student(
            String studentId,
            String firstName,
            String lastName
    ) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.submitted = false;
    }

    // getters and setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    // computed full name
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // validation
    public boolean isValid() {
        return studentId != null
                && !studentId.trim().isEmpty()
                && firstName != null
                && !firstName.trim().isEmpty()
                && lastName != null
                && !lastName.trim().isEmpty();
    }

    @Override
    public String toString() {
        return studentId + " - " + getFullName();
    }
}