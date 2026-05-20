package com.mjaquino.services;

import com.mjaquino.models.QuizSession;
import com.mjaquino.models.Exam;
import com.mjaquino.models.Student;

import java.util.HashMap;
import java.util.Map;

public class SessionService {
    // data field
    private Map<String, QuizSession> sessions;

    // constructor
    public SessionService() {
        sessions = new HashMap<>();
    }

    // checks if student have active session
    public boolean hasActiveSession(String studentId) {
        return sessions.containsKey(studentId);
    }

    // checks if more students can join
    public boolean canJoin(Exam exam) {
        return sessions.size() < exam.getMaxStudents();
    }

    // adds new student session
    public void addSession(Student student) {
        QuizSession session = new QuizSession(student);
        sessions.put(student.getStudentId(), session);
    }
    // returns student session
    public QuizSession getSession(String studentId) {
        return sessions.get(studentId);
    }
    // marks session as submitted 
    public void submitSession(String studentId) {
        QuizSession session = sessions.get(studentId);
        if(session != null) {
            session.submit();
        }
    }

    // remove session 
    public void removeSession(String studentId) {
        sessions.remove(studentId);
    }

    // returns number of active sessions
    public int getActiveCount() {
        return sessions.size();
    }
}