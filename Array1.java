import java.util.Scanner;

public class Array1 {
    public static void main(String[] args){
    
        int num[] = {6,2,3,1,5,9,7,8,4,10};
        for(int i = 0; i < num.length; i++){
            System.out.print(num[i] + "");          
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int enteredNum = input.nextInt();
        int index = -1;

        for(int i = 0; i < num.length; i++){
             if (num[i] == enteedNum);
             index = i;
             break;
        }


        System.out.println("Index: " + index);
    }
}