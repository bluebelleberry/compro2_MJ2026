import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<String[]> reportCard = new ArrayList<>();
    static final String FILE_NAME = "grades.csv";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        readData();

        do {
            System.out.println("""
                    MENU:
                    [ 1 ] Add Grades for Subject
                    [ 2 ] Display Grades
                    [ 3 ] Search Subject
                    [ 0 ] Exit
                    """);

            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                addGrades(sc);

            } else if (choice == 2) {
                displayRecords();

            } else if (choice == 3) {
                searchSubject(sc);

            } else if (choice == 0) {
                System.out.println("Bye Bye");
                break;

            } else {
                System.out.println("Invalid choice.");
            }

        } while (choice != 4);
    }

    public static void addGrades(Scanner sc) {
        try {
            String[] data = new String[4];

            System.out.print("Enter Subject Name: ");
            data[0] = sc.nextLine();

            System.out.print("Enter Prelim Grade: ");
            data[1] = String.valueOf(sc.nextDouble());

            System.out.print("Enter Midterm Grade: ");
            data[2] = String.valueOf(sc.nextDouble());

            System.out.print("Enter Final Grade: ");
            data[3] = String.valueOf(sc.nextDouble());
            sc.nextLine();

            reportCard.add(data);
            writeData(data);

            System.out.println("added successfully");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            sc.nextLine();
        }
    }

    public static void writeData(String[] data) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(FILE_NAME, true))) {

            bw.write(String.join(",", data));
            bw.newLine();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readData() {
        reportCard.clear();

        File file = new File(FILE_NAME);
        if (!file.exists())
            return;

        try (BufferedReader br = new BufferedReader(
                new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {
                reportCard.add(line.split(","));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayRecords() {
        if (reportCard.isEmpty()) {
            System.out.println("subject not found");
            return;
        }

        System.out.println("""
                Subject   Prelims   Midterm   Finals
                """);

        for (int i = 0; i < reportCard.size(); i++) {
            String[] recordSearch = reportCard.get(i);
            System.out.printf("%-10s %-7s %-7s %-7s%n", 
            recordSearch[0], 
            recordSearch[1], 
            recordSearch[2],
            recordSearch[3]);
        }
    }

    public static void searchSubject(Scanner sc) {
        System.out.print("Enter the subject you want to search: ");
        String userSearch = sc.nextLine();

        boolean foundSubject = false;

        for (int i = 0; i < reportCard.size(); i++) {
            String[] recordSearch = reportCard.get(i);
            if (recordSearch[0].equalsIgnoreCase(userSearch)) {
                System.out.println("\nSubject: " + recordSearch[0]);
                System.out.println("Prelim: " + recordSearch[1]);
                System.out.println("Midterm: " + recordSearch[2]);
                System.out.println("Final: " + recordSearch[3]);
                foundSubject = true;
                break;
            }
        }

        if (!foundSubject) {
            System.out.println("subject not found");
        }
    }
}
