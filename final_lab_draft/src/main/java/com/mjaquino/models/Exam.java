package com.mjaquino.models;

import java.util.ArrayList;

public class Exam {
    // data fields
    private ArrayList<Question> questions;
    private int timeLimit;

    // constructor
    public Exam(ArrayList<Question> questions, int timeLimit) {
        this.questions = questions;
        this.timeLimit = timeLimit;
    }

    //  add question
    public void addQuestion(Question q) {
        questions.add(q);
    }

    // remove question by ID
    public void deleteQuestion(int questionId) {
        questions.removeIf(q -> q.getQuestionId() == questionId);
    }

    // search question by ID
    public Question searchQuestion(int questionId) {
        for (Question q : questions) {
            if (q.getQuestionId() == questionId) {
                return q;
            }
        }
        return null;
    }

    // edit question text by ID
    public void editQuestion(int questionId, String newText) {
        Question q = searchQuestion(questionId);
        if (q != null) {
            q.questionText = newText;
        }
    }

    // shuffle questions
    public void shuffleQuestions() {
    
    }
    // getters
    public int getTimeLimit() {
        return timeLimit;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
