package week2;
public class TwoDimentionalArray {
    public static void main(String[] args) {
        String[] clothTypes = { "Shirts", "Pants" };
        String[][] clothColors = {
                { "red", "blue", "green" },
                { "orange", "yellow", "violet" }
        };
        for (int i = 0; i < clothColors.length; i++) {
            System.out.println(clothTypes[i]);
            for (int j = 0; j < clothColors[i].length; j++) {
                System.out.printf("%-9s", clothColors[i][j]);
            }
            System.out.println();
        }
    }
}
