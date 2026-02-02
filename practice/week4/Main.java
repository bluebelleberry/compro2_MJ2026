import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileWriter;
    
public class Main {
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();

        try(Scanner sc = new Scanner(System.in)){
            
        System.out.print("Enter First Name: ");
        sb.append(sc.nextLine());
        sb.append("\n");

        System.out.print("Enter Last Name: ");
        sb.append(sc.nextLine());
        sb.append("\n");

        System.out.print("Enter age: ");
        sb.append(sc.nextInt());
        sb.append("\n");

        sc.nextLine();

        System.out.print("Enter email: ");
        sb.append(sc.nextLine());
        sb.append("\n\n");

        System.out.print("Enter phone number: ");
        sb.append(sc.nextInt());
        sb.append("\n");

        }catch(InputMismatchException e){
            System.out.println("Invalid input");
        }

          //try-with-resource 
        try(FileWriter fw = new FileWriter("data.txt")){
            fw.write(sb.toString());
            System.out.println("Data is saved...");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
