public class TheaterSeating2D {
    public static void main(String[] args) {
        // 1. Declare and initialize the 2D array for the theater
        int[][] theater = new int[5][8]; // 5 rows, 8 columns

        // 2. Book the seat at row 2, column 5
        theater[2][5] = 1;

        // 3. Book the seat at row 0, column 0
        theater[0][0] = 1;

        System.out.println("Theater Seating Chart (0 =Avlable, 1=Booked):");
        // 4. Use nested loops to print the seating chart

        for (int i = 0; i < theater.length; i++) {
            for (int j = 0; j < theater.length; j++) {
            } 
            System.out.println(theater[i][j])
        }

        int seatBooked = 1;
        if (theater[i][j] ==0) {
                System.out.printf("Seat %d: Available\n", j + 1);
            } else {
                System.out.printf("Seat %d: Booked\n", i+ 1);
            }
    }
 }
