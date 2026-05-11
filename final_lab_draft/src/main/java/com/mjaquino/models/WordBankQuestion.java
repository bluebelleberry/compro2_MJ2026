package com.mjaquino.models;

class WordBankQuestion extends Question {
    private String questionText;
    private String[] options;

    public WordBankQuestion(String questionText, String[] options) {
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
