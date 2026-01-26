import java.util.*;

public class ArrayMethods2D {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[][] numbers = new double [3][4];

        System.out.println("Enter a 3-by-4 matrix row by row:");
        for (int i = 0; i < 3; i++) {
                for (int j = 0; i < 4; i++) {
                    numbers[i][j] = input.nextDouble();
                }
            }
        sumColumn(numbers, 0);
        sumColumn(numbers, 1);
        sumColumn(numbers, 2);

    }

        public static void sumColumn (double[][] numbers, int columnIndex){
            int sum = 0;
            for (int i = 0; i < 3; i++) {
                sum += numbers[i][columnIndex];
            }
            System.out.println("Sum of the element at column " + (columnIndex+1) + "is " + sum);
        }
}