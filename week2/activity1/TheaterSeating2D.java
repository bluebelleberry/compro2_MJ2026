import java.util.*;

public class TheaterSeating2D {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // 1. Declare and initialize the 2D array for the theater
        int[][] theater = new int[5][8]; // 5 rows, 8 columns

        // 2. Book the seat at row 2, column 5
        theater[2][5] = 1;

        // 3. Book the seat at row 0, column 0
        theater[0][0] = 1;

        //header
        System.out.println("""
                Theater Seating 
                (0 = Avlable, 1 = Booked)
                """);
        printChart(theater);

        //ask if user want to book a seat?
        System.out.print("\nBook a seat? (Y/N): ");
        char userChoice = input.next().charAt(0);

        if (userChoice == 'Y' || userChoice == 'y') {
            int row, column;
            do {
                row = (int)(Math.random() * theater.length);
                column = (int)(Math.random() * theater[0].length);
            } while (theater [row][column] == 1);

            theater[row][column] = 1;

            System.out.println("\nSuccessfully booked!!");
            System.out.println("\n1Booked seat: \nROW " + (row + 1) + "\nCOLUMN " + (column + 1) + "\n");
        } else {
            System.out.println("\nNo booked seat");
        }

        printChart(theater);
    }

        // 4. print the seating chart
        public static void printChart(int[][] theater){
            for (int i = 0; i < theater.length; i++) {
                for (int j = 0; j < theater[i].length; j++) {
                System.out.print(theater[i][j] + " ");
                }
                System.out.println();
            }
        }
}
