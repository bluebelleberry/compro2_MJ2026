package com.mjaquino.models;

class IdentificationQuestion extends Question {
    private String questionText;

    public IdentificationQuestion(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }
    
}
