package com.mjaquino.services;

import com.google.gson.*;

import com.mjaquino.models.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {
    // loads exam from a json file
    public Exam loadExam(String filePath) {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(filePath);
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            // exam information
            String title = jsonObject.get("title").getAsString();
            String password = jsonObject.get("password").getAsString();
            int durationMinutes = jsonObject.get("durationMinutes").getAsInt();
            int maxStudents = jsonObject.get("maxStudents").getAsInt();
            int passingPercentage = jsonObject.get("passingPercentage").getAsInt();
            // reads all questions
            JsonArray questionArray = jsonObject.getAsJsonArray("questions");

            List<Question> questions = new ArrayList<>();

            // converts json questions into objects
            for (JsonElement element : questionArray) {

                JsonObject questionObject = element.getAsJsonObject();

                String type = questionObject.get("type").getAsString();

                if (type.equalsIgnoreCase("identification")) {
                    questions.add(createIdentificationQuestion(questionObject));
                }

                else if (type.equalsIgnoreCase("multiple_choice")) {
                    questions.add(createMultipleChoiceQuestion(questionObject));
                }

                else if (type.equalsIgnoreCase("enumeration")) {
                    questions.add(createEnumerationQuestion(questionObject));
                }

                else if (type.equalsIgnoreCase("word_bank")) {
                    questions.add(createWordBankQuestion(questionObject));
                }
            }
            reader.close();
            // returns the completed exam object
            return new Exam(
                    title,
                    password,
                    durationMinutes,
                    maxStudents,
                    passingPercentage,
                    questions);

        } catch (Exception e) {
            System.out.println("Error loading exam: " + e.getMessage());
            return null;
        }
    }
    // converts received json question into proper object
    public Question parseQuestion(
            Gson gson,
            String type,
            String questionJson) {
        if (type == null) {
            return null;
        }

        type = type.trim().toUpperCase();
        if (type.equals("IDENTIFICATION")) {
            return gson.fromJson(
                    questionJson,
                    IdentificationQuestion.class);
        }

        if (type.equals("MULTIPLE_CHOICE")) {
            return gson.fromJson(
                    questionJson,
                    MultipleChoiceQuestion.class);
        }

        if (type.equals("ENUMERATION")) {
            return gson.fromJson(
                    questionJson,
                    EnumerationQuestion.class);
        }

        if (type.equals("WORD_BANK")) {
            return gson.fromJson(
                    questionJson,
                    WordBankQuestion.class);
        }
        return null;
    }
    // for identification questions
    private IdentificationQuestion createIdentificationQuestion(JsonObject object) {
        String question = object.get("question").getAsString();
        String answer = object.get("answer").getAsString();
        int points = object.get("points").getAsInt();

        return new IdentificationQuestion(question, answer, points);
    }
    // for multiple choices questions
    private MultipleChoiceQuestion createMultipleChoiceQuestion(JsonObject object) {
        String question = object.get("question").getAsString();
        String answer = object.get("answer").getAsString();
        int points = object.get("points").getAsInt();

        List<String> choices = new ArrayList<>();
        JsonArray choiceArray = object.getAsJsonArray("choices");

        for (JsonElement choice : choiceArray) {
            choices.add(choice.getAsString());
        }
        return new MultipleChoiceQuestion(question, choices, answer, points);
    }
    // for enumeration questions
    private EnumerationQuestion createEnumerationQuestion(JsonObject object) {
        String question = object.get("question").getAsString();
        int points = object.get("points").getAsInt();

        List<String> answers = new ArrayList<>();
        JsonArray answerArray = object.getAsJsonArray("answers");

        for (JsonElement answer : answerArray) {
            answers.add(answer.getAsString());
        }
        return new EnumerationQuestion(question, answers, points);
    }
    // for word bank questions
    private WordBankQuestion createWordBankQuestion(JsonObject object) {
        String question = object.get("question").getAsString();
        String answer = object.get("answer").getAsString();
        int points = object.get("points").getAsInt();

        List<String> choices = new ArrayList<>();
        JsonArray choiceArray = object.getAsJsonArray("choices");

        for (JsonElement choice : choiceArray) {
            choices.add(choice.getAsString());
        }
        return new WordBankQuestion(question, choices, answer, points);
    }
}