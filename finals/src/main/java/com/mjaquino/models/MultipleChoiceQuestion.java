package com.mjaquino.models;

import java.util.List;

public class MultipleChoiceQuestion extends Question {
    // data fields
    private List<String> choices;
    private String answer;

    // empty constructor
    public MultipleChoiceQuestion() {
    }

    // constructor
    public MultipleChoiceQuestion(
            String question,
            List<String> choices,
            String answer,
            int points) {
        super(QuestionType.MULTIPLE_CHOICE, question, points);
        this.choices = choices;
        this.answer = answer;
    }

    // getters and setters
    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // check the answer
    @Override
    public boolean checkAnswer(Object userAnswer) {
        if (userAnswer == null) {
            return false;
        }
        String input = userAnswer.toString().trim().toUpperCase();

        if (input.isEmpty()) {
            return false;
        }
        if (input.length() == 1) {
            int index = input.charAt(0) - 'A';
            if (index >= 0 && index < choices.size()) {
                return choices.get(index).equalsIgnoreCase(answer);
            }
        }
        return answer.equalsIgnoreCase(input);
    }

    // display questions
    @Override
    public void displayQuestion() {
        System.out.println(getQuestion());
        char letter = 'A';
        for (String choice : choices) {
            System.out.println(letter + ". " + choice);
            letter++;
        }
    }

    // get the correct answers
    @Override
    public Object getCorrectAnswer() {
        return answer;
    }

    // get the scores
    @Override
    public int getScore(Object userAnswer) {
        return checkAnswer(userAnswer) ? getPoints() : 0;
    }
}