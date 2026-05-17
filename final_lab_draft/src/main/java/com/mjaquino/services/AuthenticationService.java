package com.mjaquino.services;

import com.mjaquino.models.AuthenticationRequest;
import com.mjaquino.models.Exam;
import com.mjaquino.models.Student;

public class AuthenticationService {
    // date fields
    private SessionService sessionService;
    private ResultService resultService;

    // constructor 
    public AuthenticationService(
            SessionService sessionService,
            ResultService resultService
    ) {
        this.sessionService = sessionService;
        this.resultService = resultService;
    }

    // authenticate a student before entering the quiz
    public boolean authenticate(
            AuthenticationRequest request,
            Exam exam
    ) {
        if(request == null || exam == null) {
            return false;
        }
        if(isEmpty(request.getStudentId())
                || isEmpty(request.getFirstName())
                || isEmpty(request.getLastName())
                || isEmpty(request.getPassword())) {
            return false;
        }
        if(!exam.getPassword().equals(request.getPassword())) {
            return false;
        }
        if(sessionService.hasActiveSession(request.getStudentId())) {
            return false;
        }
        if(resultService.hasStudentRecord(request.getStudentId())) {
            return false;
        }
        if(!sessionService.canJoin(exam)) {
            return false;
        }
        sessionService.addSession(request.toStudent());
        return true;
    }
    // checks if a string is empty
    private boolean isEmpty(String value) {
        return value == null
                || value.trim().isEmpty();
    }
}