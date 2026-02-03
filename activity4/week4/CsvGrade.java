import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CsvGrade {
    static String[] subject;
    static double[][] grades;

    public static void main(String[] args) {
        subject = new String[50];
        grades = new double[50][3];

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
                for (int r = 0; r < subject.length; r++) {
                    sc.nextLine();

                    System.out.print("Enter subject: ");
                    subject[r] = sc.nextLine();

                    System.out.print("Enter Prelim grades: ");
                    try {
                        grades[r][0] = sc.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid number");
                        sc.nextLine();
                    }

                    System.out.print("Enter Midterm grades: ");
                    try {
                        grades[r][1] = sc.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid number");
                        sc.nextLine();
                    }

                    System.out.print("Enter Final term grades: ");
                    try {
                        grades[r][2] = sc.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid number");
                        sc.nextLine();
                    }

                    sc.nextLine();
                    System.out.println();

                    writeData();
                    break;
                }
            } else if (choice == 2) {
                System.out.println("Bye Bye");
            }

        } while (choice != 2);
    }

    public static void writeData() {
        StringBuilder sb = new StringBuilder();

        sb.append("Subject,Prelim,Midterm,Final\n");
        for (int r = 0; r < subject.length; r++) {
            if (subject[r] == null)
                break;

            sb.append(subject[r]);
            for (int c = 0; c < grades[r].length; c++) {
                sb.append(",").append(grades[r][c]);
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
