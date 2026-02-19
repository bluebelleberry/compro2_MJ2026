public class TheaterSeating2D {
    public static void main(String[] args) {
        //declare and initialize 2D array
        int[][] theater = new int[5][8];
        //book seats
        theater[2][5] = 1;
        theater[0][0] = 1;
        System.out.println("Theater Seating Chart (0=Available, 1=Booked):");
        //nested loop
        //display seating chart 
        int bookedCount = 0;
        //outer loop for rows
        for (int i = 0; i < theater.length; i++) {
            //inner loop for column
            for (int j = 0; j < theater[i].length; j++) {
                System.out.print(theater[i][j] + " ");
                if (theater[i][j] == 1) {
                    bookedCount++;
                }
            }
            System.out.println();
        }
        //display total booked seats
        System.out.println("\nTotal booked seats: " + bookedCount);
    }
}
