package com.mjaquino;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws IOException {
        /***
                    String json = """
                {
                "firstName":"John",
                "lastName":"Doe",
                "age":25,
                "emailAddress":"john.doe@example.com",
                "phoneNumber":"+15550101",
                "dateOfBirth":"1999-05-15",
                "homeAddress":"123 Java Lane",
                "isEmployed": true,
                "nationality":"Canadian",
                "gender":"Non-binary"
                }
                """;
         */
        Person person = new Person("John" , "Doe", 25, "john.doe@example.com", "+15550101", "1999-05-15", "123 Java Lane", true, "Canadian", "Non-binary");

        //create gson object to be used for serialization 
        Gson gson = new Gson();
        //serialization person object and print the json data
        String json = gson.toJson(person);
        System.out.println(json);

        //write json data to file
        FileWriter fw = new FileWriter("data/newperson.json");
        gson.toJson(person, fw);
        fw.close();

        /***
        Person person1 = gson.fromJson(json, Person.class);
        System.out.println("Hello!");
        System.out.println(person1.toString());
        */
    }

}