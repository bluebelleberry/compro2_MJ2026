package com.phonebook;

import java.util.Scanner;

import com.phonebook.models.Contact;
import com.phonebook.services.PhonebookService;

public class Main {
    public static void main(String[] args) {

        PhonebookService pbservice = new PhonebookService();
        pbservice.loadFromCSV("contacts.csv");

        Scanner sc = new Scanner(System.in);

        int userChoice;

        do {
            System.out.println("""
                    Contact Menu:
                    | 1 | Add
                    | 2 | Search
                    | 3 | Remove
                    | 4 | Display All
                    | 5 | Save to CSV
                    | 0 | Exit
                    """);

            System.out.print("Choose: ");
            userChoice = sc.nextInt();
            sc.nextLine();

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

                        System.out.println("Contact added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter name to search: ");
                        String searchName = sc.nextLine();

                        Contact found = pbservice.searchContact(searchName);

                        if (found != null) {
                            System.out.println(found);
                        } else {
                            System.out.println("Contact not found.");
                        }

                        break;

                    case 3:
                        System.out.print("Enter name to remove: ");
                        String removeName = sc.nextLine();

                        pbservice.removeContact(removeName);
                        System.out.println("Contact removed (if it existed).");
                        break;

                    case 4:
                        pbservice.displayAllContacts();
                        break;

                    case 5:
                        pbservice.saveToCSV("contacts.csv");
                        break;

                    case 0:
                        pbservice.saveToCSV("contacts.csv");
                        System.out.println("Exiting program...");
                        break;

                    default:
                        System.out.println("Invalid choice.");

                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (userChoice != 0);

        sc.close();
    }
}