package com.mjaquino;

import com.mjaquino.model.Grade;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Grade> gradeList = new ArrayList<>();

    static final String FILE_NAME = "data/grades.json";
    static boolean hasChanges = false;
    static long lastModifiedTime = 0;

    public static void main(String[] args) {

        readFile();

        //thread 1 save
        Thread writerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);

                    if (hasChanges) {
                        saveToDisk();
                        hasChanges = false;
                    }

                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        //thread 2 read
        Thread readerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);

                    File file = new File(FILE_NAME);
                    if (file.exists()) {
                        long currentModified = file.lastModified();

                        if (currentModified != lastModifiedTime) {
                            readFile();
                            lastModifiedTime = currentModified;
                        }
                    }

                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        writerThread.setDaemon(true);
        readerThread.setDaemon(true);

        writerThread.start();
        readerThread.start();

        boolean mainLoop = true;

        while (mainLoop) {
            System.out.println("""

                    Main Menu
                    [ 1 ] Enter Grades
                    [ 2 ] Display Grades
                    [ 3 ] Exit
                    """);

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    enterGrades();
                    break;

                case 2:
                    displayGrades();
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    mainLoop = false;
                    break;

                default:
                    System.out.println("Invalid choice\n");
            }
        }
    }

    // option 2 inputing grades
    public static void enterGrades() {

        input.nextLine();

        System.out.print("Enter subject: ");
        String subject = input.nextLine().toUpperCase();

        System.out.print("Prelim: ");
        int prelim = input.nextInt();

        System.out.print("Midterm: ");
        int midterm = input.nextInt();

        System.out.print("Final: ");
        int fin = input.nextInt();

        boolean found = false;

        for (Grade g : gradeList) {
            if (g.getSubject().equalsIgnoreCase(subject)) {
                g.setPrelim(prelim);
                g.setMidterm(midterm);
                g.setFin(fin);
                found = true;
                break;
            }
        }

        if (!found) {
            gradeList.add(new Grade(subject, prelim, midterm, fin));
        }

        hasChanges = true;

        System.out.println("grades saved\n");
    }

    // option 2 displaying updated grades
    public static void displayGrades() {

        System.out.println("""

                Subject   Pre   Mid   Fin
                """);

        for (Grade g : gradeList) {
            System.out.printf("%-8s %3d %5d %5d%n",
                    g.getSubject(),
                    g.getPrelim(),
                    g.getMidterm(),
                    g.getFin());
        }

        System.out.println();
    }

    // saving to the file
    public static void saveToDisk() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (int i = 0; i < gradeList.size(); i++) {
                Grade g = gradeList.get(i);

                bw.write("{\n");
                bw.write("  \"subject\": \"" + g.getSubject() + "\",\n");
                bw.write("  \"prelim\": " + g.getPrelim() + ",\n");
                bw.write("  \"midterm\": " + g.getMidterm() + ",\n");
                bw.write("  \"final\": " + g.getFin() + "\n");
                bw.write("}");

                if (i < gradeList.size() - 1) {
                    bw.write(",");
                }
                bw.write("\n");
            }

            File file = new File(FILE_NAME);
            lastModifiedTime = file.lastModified();

        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    // reading the file
    public static void readFile() {

        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        ArrayList<Grade> tempList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            String subject = "";
            int prelim = 0, midterm = 0, fin = 0;

            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (line.startsWith("\"subject\"")) {
                    subject = extractString(line);

                } else if (line.startsWith("\"prelim\"")) {
                    prelim = extractInt(line);

                } else if (line.startsWith("\"midterm\"")) {
                    midterm = extractInt(line);

                } else if (line.startsWith("\"final\"")) {
                    fin = extractInt(line);

                    tempList.add(new Grade(subject, prelim, midterm, fin));
                }
            }

            gradeList = tempList;

        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }

    // extracting
    public static String extractString(String line) {
        String val = line.substring(line.indexOf(":") + 1).trim();
        if (val.endsWith(",")) {
            val = val.substring(0, val.length() - 1);
        }
        return val.replace("\"", "");
    }

    public static int extractInt(String line) {
        String val = line.substring(line.indexOf(":") + 1).trim();
        if (val.endsWith(",")) {
            val = val.substring(0, val.length() - 1);
        }
        return Integer.parseInt(val);
    }
}