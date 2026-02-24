package com.phonebook;

import java.util.Scanner;

import com.phonebook.models.Contact;
import com.phonebook.services.PhonebookService;

public class Main {
    public static void main(String[] args) {
        PhonebookService pbservice = new PhonebookService();

        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Contact Menu:
                | 1 | Add
                | 2 | Search
                | 3 | Remove
                | 4 | Display All
                | 5 | Save to CSV
                | 0 | Exit
                    """);
        int userChoice = sc.nextInt();

        try {
            switch (userChoice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter phone number: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    Contact contact = new Contact(name, phone, email);
                    pbservice.addContact(contact);

                    System.out.println("your contacts are added successfully!");
                    break;
                case 2:
                    System.out.print("Enter name to search: ");
                case 3:
                    System.out.print("Enter name to remove: ");
                    String removeName = sc.nextLine();

                    pbservice.removeContact(removeName);
                    System.out.println("Contact removed (if it existed).");
                    break;
                case 4:
                case 5:
                case 0:
            }
        } catch (Exception e) {
            System.out.println("Error saving to file, " + e.getMessage());
        }
    }
}