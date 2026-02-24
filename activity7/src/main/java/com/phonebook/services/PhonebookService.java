package com.phonebook.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.phonebook.models.Contact;

public class PhonebookService {
    private HashMap<String, Contact> contacts;

    // methods
    public void addContact(Contact c) {
        contacts.put(c.getName(), c);
    }

    public void searchContact(String name) {
        contacts.get(name);
    }

    public void removeContact(String name) {
        contacts.remove(name);
    }

    public void saveToCSV(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            for (Contact contact : contacts.values()) {
                writer.write(contact.getName() + "," + contact.getphoneNumber() + "," + contact.getEmail());
                writer.newLine();
            }

            System.out.println("Contacts saved successfully to " + filename);

        } catch (IOException e) {
            System.out.println("Error saving contacts, " + e.getMessage());
        }
    }
}
