package week2;

public class Week2activity1 {
    public static void main(String[] args) {
        int[] theaterRow = {0,0,0,0,0,0,0};
        theaterRow[3] = 1;

        for (int i = 0; i < theaterRow.length; i++) {
            
            if (theaterRow[i] == 0){
                System.out.printf("Seat %d: Available\n", i+1);
            } else {
                System.out.printf("Seat %d: Booked\n", i+1);
            }             
        }System.out.println();   
    }

}

