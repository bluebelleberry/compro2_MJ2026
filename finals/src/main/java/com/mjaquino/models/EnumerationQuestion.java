package com.mjaquino.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnumerationQuestion extends Question {
    // data field
    private List<String> answers;

    // empty constructor
    public EnumerationQuestion() {
    }

    // constructor
    public EnumerationQuestion(
            String question,
            List<String> answers,
            int points) {
        super(QuestionType.ENUMERATION, question, points);
        this.answers = answers;
    }

    // getter
    public List<String> getAnswers() {
        return answers;
    }

    // setter
    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    // check if answer are correct
    @Override
    public boolean checkAnswer(Object userAnswer) {
        if (userAnswer == null) {
            return false;
        }
        if (!(userAnswer instanceof List<?>)) {
            return false;
        }
        List<String> inputAnswers = (List<String>) userAnswer;
        Set<String> correctSet = new HashSet<>();
        Set<String> inputSet = new HashSet<>();
        // converting to lowercase 
        for (String answer : answers) {
            correctSet.add(answer.trim().toLowerCase());
        }
        for (String input : inputAnswers) {
            inputSet.add(input.trim().toLowerCase());
        }
        return correctSet.equals(inputSet);
    }

    // display the question
    @Override
    public void displayQuestion() {
        System.out.println(getQuestion());
    }

    // get correct answers
    @Override
    public Object getCorrectAnswer() {
        return answers;
    }

    // computes score
    @Override
    public int getScore(Object userAnswer) {
        if (userAnswer == null) {
            return 0;
        }
        if (!(userAnswer instanceof List<?>)) {
            return 0;
        }
        List<String> inputAnswers = (List<String>) userAnswer;
        Set<String> correctSet = new HashSet<>();
        Set<String> inputSet = new HashSet<>();
        for (String answer : answers) {
            correctSet.add(answer.trim().toLowerCase());
        }
        for (String input : inputAnswers) {
            inputSet.add(input.trim().toLowerCase());
        }
        int correctCount = 0;
        for (String input : inputSet) {
            if (correctSet.contains(input)) {
                correctCount++;
            }
        }
        return correctCount;
    }
}