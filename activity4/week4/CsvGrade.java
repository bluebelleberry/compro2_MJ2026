import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CsvGrade {
    static String[] subject = new String[50];
    static double[][] grades = new double[50][3];
    static int subjectCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("""
                    MENU:
                        [ 1 ] Add Grades for Subject
                        [ 2 ] Exit
                        """);
            System.out.print("Choice: ");
            choice = sc.nextInt();

            if (choice == 1) {

                if (subjectCount >= subject.length) {
                    System.out.println("Subject list is full");
                    continue;
                }

                sc.nextLine();

                System.out.print("Enter Subject: ");
                subject[subjectCount] = sc.nextLine();

                System.out.print("Enter Prelim Grades: ");
                try {
                    grades[subjectCount][0] = sc.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid number");
                    sc.nextLine();
                }

                System.out.print("Enter Midterm Grades: ");
                try {
                    grades[subjectCount][1] = sc.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid number");
                    sc.nextLine();
                }

                System.out.print("Enter Final Grades: ");
                try {
                    grades[subjectCount][2] = sc.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid number");
                    sc.nextLine();
                }

                subjectCount++;
                sc.nextLine();
                writeData();

            } else if (choice == 2) {
                System.out.println("Bye Bye");
            }

        } while (choice != 2);
    }

    public static void writeData() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSubject, Prelim, Midterm, Finals\n");

        for (int r = 0; r < subjectCount; r++) {
            sb.append(subject[r]);
            for (int c = 0; c < 3; c++) {
                sb.append(", ").append(grades[r][c]);
            }
            sb.append("\n");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.csv"))) {
            bw.write(sb.toString());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(sb.toString());
    }
}


