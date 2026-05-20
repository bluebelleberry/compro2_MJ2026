package com.mjaquino.services;

import com.mjaquino.models.Question;
import com.mjaquino.models.Exam;
import com.mjaquino.models.IdentificationQuestion;
import com.mjaquino.models.EnumerationQuestion;
import com.mjaquino.models.MultipleChoiceQuestion;
import com.mjaquino.models.WordBankQuestion;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizService {
    // data fields
    public List<Question> prepareQuestions(Exam exam) {
        // stores question types
        List<Question> identification = new ArrayList<>();
        List<Question> enumeration = new ArrayList<>();
        List<Question> multipleChoice = new ArrayList<>();
        List<Question> wordBank = new ArrayList<>();

        // separates questions by type
        for(Question question : exam.getQuestions()) {
            if(question instanceof IdentificationQuestion) {
                identification.add(question);
            }

            else if(question instanceof EnumerationQuestion) {
                enumeration.add(question);
            }

            else if(question instanceof MultipleChoiceQuestion) {
                MultipleChoiceQuestion mcq =
                        (MultipleChoiceQuestion) question;
                // randomizes choices
                shuffleChoices(mcq.getChoices());
                multipleChoice.add(mcq);
            }

            else if(question instanceof WordBankQuestion) {
                WordBankQuestion wbq =
                        (WordBankQuestion) question;
                // randomizes word bank choices
                shuffleChoices(wbq.getChoices());
                wordBank.add(wbq);
            }
        }
        // randomizes order insides each question types
        Collections.shuffle(identification);
        Collections.shuffle(enumeration);
        Collections.shuffle(multipleChoice);
        Collections.shuffle(wordBank);

        // for the question groups
        List<List<Question>> groups = new ArrayList<>();
        
        if(!identification.isEmpty()) {
            groups.add(identification);
        }

        if(!enumeration.isEmpty()) {
            groups.add(enumeration);
        }

        if(!multipleChoice.isEmpty()) {
            groups.add(multipleChoice);
        }

        if(!wordBank.isEmpty()) {
            groups.add(wordBank);
        }

        // randomizes group order
        Collections.shuffle(groups);
        // combining all groups
        List<Question> finalQuestions = new ArrayList<>();

        for(List<Question> group : groups) {
            finalQuestions.addAll(group);
        }
        return finalQuestions;
    }
    // randomizes answer choices
    private void shuffleChoices(List<String> choices) {
        if(choices != null) {
            Collections.shuffle(choices);
        }
    }
}