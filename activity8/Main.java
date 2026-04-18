package com.mjaquino;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("COMPRO2"));
        subjects.add(new Subject("OOP"));
        subjects.add(new Subject("DATSTRU"));
        subjects.add(new Subject("MMW"));
        subjects.add(new Subject("UTS"));

        boolean mainLoop = true;

        while (mainLoop) {
            System.out.println("""
                    
                    Main Menu
                    [ 1 ] Enter Grades
                    [ 2 ] Display Grades
                    [ 3 ] Exit
                    """);

            System.out.print("Enter your choice: ");
            int menuChoice = readInt(input);

            if (menuChoice == 1) {
                boolean subjectGrades = true;

                while (subjectGrades) {
                    System.out.println("""
                            
                            Enter Grades
                            [ 1 ] COMPRO2
                            [ 2 ] OOP
                            [ 3 ] DATSTRU
                            [ 4 ] MMW
                            [ 5 ] UTS
                            [ 0 ] Back
                            """);

                    System.out.print("Enter your choice: ");
                    int subjectChoice = readInt(input);

                    if (subjectChoice == 0) {
                        subjectGrades = false;
                    } else if (subjectChoice >= 1 && subjectChoice <= subjects.size()) {
                        Subject selectedSubject = subjects.get(subjectChoice - 1);

                        System.out.println("\nEnter grades for " + selectedSubject.getName());

                        System.out.print("Prelim: ");
                        int prelim = readInt(input);

                        System.out.print("Midterm: ");
                        int midterm = readInt(input);

                        System.out.print("Final: ");
                        int finals = readInt(input);

                        selectedSubject.setGrades(prelim, midterm, finals);

                        System.out.println("Grades saved!\n");

                        writeJSON(subjects);
                    } else {
                        System.out.println("Invalid choice\n");
                    }
                }

            } else if (menuChoice == 2) {
                System.out.println("""
                        
                        Subject   Pre   Mid   Fin
                        """);

                for (Subject subject : subjects) {
                    System.out.printf("%-8s %3d %5d %5d%n",
                            subject.getName(),
                            subject.getPrelim(),
                            subject.getMidterm(),
                            subject.getFinals());
                }

                System.out.println();

            } else if (menuChoice == 3) {
                System.out.println("Exiting program...");
                mainLoop = false;
            } else {
                System.out.println("Invalid choice\n");
            }
        }

        input.close();
    }

    public static int readInt(Scanner input) {
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Enter a number: ");
                input.next();
            }
        }
    }

    public static void writeJSON(ArrayList<Subject> subjects) {
        StringBuilder json = new StringBuilder();


        for (int i = 0; i < subjects.size(); i++) {
            Subject subject = subjects.get(i);

            json.append("    {\n");
            json.append("      \"subject\": \"").append(subject.getName()).append("\",\n");
            json.append("      \"prelim\": ").append(subject.getPrelim()).append(",\n");
            json.append("      \"midterm\": ").append(subject.getMidterm()).append(",\n");
            json.append("      \"final\": ").append(subject.getFinals()).append("\n");
            json.append("    }");

            if (i < subjects.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("grades.json"))) {
            bw.write(json.toString());
        } catch (IOException e) {
            System.out.println("Error saving JSON: " + e.getMessage());
        }
    }
}