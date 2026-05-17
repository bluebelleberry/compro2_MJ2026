package com.mjaquino.services;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import com.mjaquino.models.ExamResult;
import com.mjaquino.models.Student;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResultService {
    // data fields 
    private final String filePath = "data/record.json";
    private final Gson gson;

    // constructor 
    public ResultService() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }
    // save exam result to json file
    public void saveResult(ExamResult result) {
        List<ExamResult> results = loadResults();
        results.add(result);
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            gson.toJson(results, writer);
            writer.close();
            System.out.println("Result saved successfully.");
        } catch(Exception e) {
            System.out.println("Error saving result: " + e.getMessage());
        }
    }
    // load all saved exam results from json file
    public List<ExamResult> loadResults() {
        try {
            File file = new File(filePath);
            if(!file.exists()) {
                return new ArrayList<>();
            }
            FileReader reader = new FileReader(file);
            Type listType = new TypeToken<List<ExamResult>>() {}.getType();
            List<ExamResult> results = gson.fromJson(reader, listType);
            reader.close();
            if(results == null) {
                return new ArrayList<>();
            }
            return results;
        } catch(Exception e) {
            System.out.println("Error loading results: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    // checks if the student already has a saved record
    public boolean hasStudentRecord(String studentId) {
        List<ExamResult> results = loadResults();
        for(ExamResult result : results) {
            if(result.getStudentId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }
}