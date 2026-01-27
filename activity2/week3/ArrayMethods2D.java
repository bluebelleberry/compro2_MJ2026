import java.util.*;

public class ArrayMethods2D {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rows = 3;
        int column = 4;
        double[][] numbers = new double [rows][column];

        System.out.println("Enter a 3-by-4 matrix row by row:");
        for (int r = 0; r < rows; r++) {
                for (int c = 0; c < column; c++) {
                    numbers[r][c] = input.nextDouble();
                }
            }
        
        for (int c = 0; c < column; c++) {
            System.out.println("Sum of the element at column " + c + " is " + sumColumn(numbers, c));
        }
    }

        public static double sumColumn (double[][] numbers, int column){
            double sum = 0;
            for (int row = 0; row < numbers.length; row++) {
                sum += numbers[row][column];
            }
            return sum;
        }
        public static double sumDiagonal(double[][] numbers) {

        }
}