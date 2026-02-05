import java.util.*;

public class ArrayMethods2D {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //prob 1
        int rows = 3;
        int column = 4;
        double[][] table1 = new double [rows][column];

        System.out.println("Enter a 3-by-4 matrix row by row:");
        for (int r = 0; r < rows; r++) {
                for (int c = 0; c < column; c++) {
                    table1[r][c] = input.nextDouble();
                }
            }
        
        for (int c = 0; c < column; c++) {
            System.out.println("Sum of the element at column " + c + " is " + sumColumn(table1, c));
        }
        
        //prob 2
        int rcSize = 4;
        double[][] table2 = new double [rcSize][rcSize];

        System.out.println("Enter a 4-by-4 matrix row by row:");
        for (int row = 0; row < rcSize; row++) {
                for (int col = 0; col < rcSize; col++) {
                    table1[row][col] = input.nextDouble();
                }
            }
        System.out.println("Sum of the element major diagonal is " + sumDiagonal(table2));

    }
        //prob 1
        public static double sumColumn (double[][] numbers, int column){
            double sum = 0;
            for (int row = 0; row < numbers.length; row++) {
                sum += numbers[row][column];
            }
            return sum;
        }
        //prob 2
        public static double sumDiagonal(double[][] numbers) {
            double sum = 0;
            for (int i = 0; i < numbers.length; i++) {
                sum += numbers[i][i];
            }
            return sum;
        }
}