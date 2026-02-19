public class TheaterSeating {
    public static void main(String[] args) {
        //8 seats
        int[] theaterRow = new int[8];
        //book a seat at index 3
        theaterRow[3] = 1;
        System.out.println("Seat Status (0=Available, 1=Booked):");
        //display seats
        int availableCount = 0;
        for (int i = 0; i < theaterRow.length; i++) {
            System.out.print(theaterRow[i] + " ");
            if (theaterRow[i] == 0) {
                availableCount++;
            }
        }
        //display available seats
        System.out.println("\nAvailable seats: " + availableCount);
    }
}
