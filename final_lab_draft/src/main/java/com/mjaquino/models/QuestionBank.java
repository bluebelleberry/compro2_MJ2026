package com.mjaquino.models;

import java.util.ArrayList;

public class QuestionBank {
    // data fields
    private ArrayList<Question> questions;
    private ArrayList<String> wordBankChoices;

    // constructor
    public QuestionBank(ArrayList<Question> questions, ArrayList<String> wordBankChoices) {

        this.questions = questions;
        this.wordBankChoices = wordBankChoices;
    }

    // getter for questions
    public ArrayList<Question> getQuestions() {
        return questions;
    }
    // getter for word bank choices
    public ArrayList<String> getWordBankChoices() {
        return wordBankChoices;
    }
}
