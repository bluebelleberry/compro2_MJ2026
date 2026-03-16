package com.phonebook.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.phonebook.models.Contact;

public class PhonebookService {
    private HashMap<String, Contact> contacts = new HashMap<>();

    // methods
    public void addContact(Contact c) {
        contacts.put(c.getName(), c);
    }

    public Contact searchContact(String name) {
        contacts.get(name);
    }

    public void removeContact(String name) {
        contacts.remove(name);
    }
    //display
    public void displayAllContacts() {

        if (contacts.isEmpty()) {
            System.out.println("contacts not found");
            return;
        }

        for (Contact contact : contacts.values()) {
            System.out.println(contact);
        }
    }
    //save to csv
    public void saveToCSV(String filename) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            for (Contact contact : contacts.values()) {
                writer.write(contact.toCsvString());
                writer.newLine();
            }

            System.out.println("contacts are successfully save");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //read csv file libe by line
    public void loadFromCSV(String filename) {

        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("file not found");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length == 3) {

                    String name = parts[0];
                    String phone = parts[1];
                    String email = parts[2];

                    Contact contact = new Contact(name, phone, email);
                    contacts.put(name, contact);
                }
            }

            System.out.println("contacts successfully load");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}