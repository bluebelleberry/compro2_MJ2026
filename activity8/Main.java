package com.mjaquino;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] subjects = {
                "COMPRO2",
                "OOP",
                "DATSTRU",
                "MMW",
                "UTS"
        };

        int[][] grades = new int[5][3];

        boolean mainLoop = true;

        while (mainLoop) {

            System.out.println("""

                    Main Menu
                    [ 1 ] Enter Grades
                    [ 2 ] Display Grades
                    [ 3 ] Exit
                    """);

            System.out.print("Enter your choice: ");
            int menuChoice = input.nextInt();

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
                    int subjectChoice = input.nextInt();

                    if (subjectChoice == 0) {
                        subjectGrades = false;
                    }

                    else if (subjectChoice >= 1 && subjectChoice <= 5) {

                        int subjectIndex = subjectChoice - 1;

                        System.out.println("\nEnter grades for " + subjects[subjectIndex]);

                        System.out.print("Prelim: ");
                        grades[subjectIndex][0] = input.nextInt();

                        System.out.print("Midterm: ");
                        grades[subjectIndex][1] = input.nextInt();

                        System.out.print("Final: ");
                        grades[subjectIndex][2] = input.nextInt();

                        System.out.println("Grades saved!\n");

                        writeJSON(subjects, grades);
                    }

                    else {
                        System.out.println("Invalid choice\n");
                    }
                }

            }

            else if (menuChoice == 2) {

                System.out.println("""

                        Subject   Pre   Mid   Fin
                        """);

                for (int i = 0; i < subjects.length; i++) {
                    System.out.printf("%-8s %3d %5d %5d%n",
                            subjects[i],
                            grades[i][0],
                            grades[i][1],
                            grades[i][2]);
                }

                System.out.println();
            }

            else if (menuChoice == 3) {
                System.out.println("Exiting program...");
                mainLoop = false;
            }

            else {
                System.out.println("Invalid choice\n");
            }
        }
    }

    public static void writeJSON(String[] subjects, int[][] grades) {

        StringBuilder json = new StringBuilder();

        for (int i = 0; i < subjects.length; i++) {

            json.append("    {\n");
            json.append("      \"subject\": \"").append(subjects[i]).append("\",\n");
            json.append("      \"prelim\": ").append(grades[i][0]).append(",\n");
            json.append("      \"midterm\": ").append(grades[i][1]).append(",\n");
            json.append("      \"final\": ").append(grades[i][2]).append("\n");
            json.append("    }");

            if (i < subjects.length - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("  ]\n");
        json.append("}");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("grades.json"))) {
            bw.write(json.toString());
        }

        catch (IOException e) {
            System.out.println("Error saving JSON: " + e.getMessage());
        }
    }
}