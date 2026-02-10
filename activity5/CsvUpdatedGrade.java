import java.io.*;
import java.util.*;

public class CsvUpdatedGrade {

    static String[] subject = new String[50];
    static double[][] grades = new double[50][3];
    static int subjectCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            //menu
            System.out.println("""
                    MENU:
                    [ 1 ] Add Grades for Subject
                    [ 2 ] Display Grades
                    [ 3 ] Search Subject 
                    [ 4 ] Exit
                        """);
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            //choices
            //choice 1

            if (choice == 1) {

                if (subjectCount >= subject.length) {
                    System.out.println("Subject list is full.");
                    continue;
                }

                try {
                    System.out.print("Enter Subject Name: ");
                    subject[subjectCount] = sc.nextLine();

                    System.out.print("Enter Prelim Grade: ");
                    grades[subjectCount][0] = sc.nextDouble();

                    System.out.print("Enter Midterm Grade: ");
                    grades[subjectCount][1] = sc.nextDouble();

                    System.out.print("Enter Final Grade: ");
                    grades[subjectCount][2] = sc.nextDouble();

                    subjectCount++;

                } catch (InputMismatchException e) {
                    System.out.println("Invalid number input.");
                    sc.nextLine();
                }

            } else if (choice == 2) {
                writeData();

            } else if (choice == 3) {


            } else if (choice == 4) {
                System.out.println("Bye Bye");

            } else {
                System.out.println("Invalid menu choice.");
            }

        } while (choice != 4);

        sc.close();
    }

    public static void writeData() {
        StringBuilder sb = new StringBuilder();
        sb.append("Subject, Prelim, Midterm, Final\n");

        for (int i = 0; i < subjectCount; i++) {
            sb.append(subject[i]);
            for (int j = 0; j < 3; j++) {
                sb.append(",").append(grades[i][j]);
            }
            sb.append("\n");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.csv"))) {
            bw.write(sb.toString());
            System.out.println("\nData saved to data.csv");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nGRADES");
        System.out.println(sb);
    }

    public static void search(String s){
        System.out.println("Search grades: ");
    
    }
}

