package com.mjaquino.models;

public class IdentificationQuestion extends Question {
    // data field
    private String answer;

    // empty constructor
    public IdentificationQuestion() {}

    // constructor
    public IdentificationQuestion(
            String question,
            String answer,
            int points
    ) {
        super(QuestionType.IDENTIFICATION, question, points);
        this.answer = answer;
    }

    // getter 
    public String getAnswer() {
        return answer;
    }
    // setter
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // check answers
    @Override
    public boolean checkAnswer(Object userAnswer) {
        if(userAnswer == null) {
            return false;
        }
        String input = userAnswer.toString().trim();
        return answer.equalsIgnoreCase(input);
    }

    // display questions
    @Override
    public void displayQuestion() {
        System.out.println(question);
    }

    // get the correct answers
    @Override
    public Object getCorrectAnswer() {
        return answer;
    }

    // get score
    @Override
public int getScore(Object userAnswer) {
    return checkAnswer(userAnswer) ? getPoints() : 0;
}
}