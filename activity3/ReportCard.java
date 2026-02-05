import java.util.Scanner;

public class ReportCard {

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
                \nMain Menu
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
                        \nEnter Grades
                        [ 1 ] COMPRO 2
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

                        System.out.printf("\nEnter grades for %s: ",subjects[subjectIndex]);

                        grades[subjectIndex][0] = input.nextInt(); // prelims
                        grades[subjectIndex][1] = input.nextInt(); // midterms
                        grades[subjectIndex][2] = input.nextInt(); // final

                        System.out.println("Grades saved!\n");
                    }
                    else {
                        System.out.println("Invalid choice. Try again.\n");
                    }
                }

            }
            else if (menuChoice == 2) {

                System.out.println("""
                    Subject  Pre   Mid   Fin
                    """);

                for (int i = 0; i < subjects.length; i++) {
                    System.out.printf("%-8s %3d  %4d  %4d%n",
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
                System.out.println("Invalid choice.\n");
            }
        }
    }
}
