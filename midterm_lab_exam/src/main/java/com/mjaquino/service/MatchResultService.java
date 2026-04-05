package com.mjaquino.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mjaquino.model.Account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
// handles reading and writing to json file
public class MatchResultService {
    // json file name
    private static final String FILE_NAME = "playersMatchResult.json";

    public static List<Account> loadAccounts() {
        File file = new File(FILE_NAME);
        // if account existing it will then read it to json file and used it
        if (!file.exists()) {
            return new ArrayList<>();
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Account>>() {}.getType();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<Account> accounts = gson.fromJson(reader, listType);
            return accounts != null ? accounts : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    // saving the acoounts to json file
    public static void saveAccounts(List<Account> accounts) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(gson.toJson(accounts));
        } catch (IOException e) {
            System.out.println("Failed to save accounts.");
        }
    }
}