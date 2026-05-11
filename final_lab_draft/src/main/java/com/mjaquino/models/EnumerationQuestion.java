package com.mjaquino.models;

class EnumerationQuestion extends Question {
    private String questionText;
    private String[] options;

    public EnumerationQuestion(String questionText, String[] options) {
        this.questionText = questionText;
        this.options = options;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }
    
}
