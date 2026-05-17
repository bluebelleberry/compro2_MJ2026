package com.mjaquino.models;

public class AuthenticationRequest {
    // data fields
    private String studentId;
    private String firstName;
    private String lastName;
    private String password;

    // empty constructor
    public AuthenticationRequest() {
    }

    // constructor
    public AuthenticationRequest(
            String studentId,
            String firstName,
            String lastName,
            String password
    ) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    // getters
    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    // for the authentication request 
    public Student toStudent() {
        return new Student(studentId, firstName, lastName);
    }
}