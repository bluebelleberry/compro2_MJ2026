import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client{
    public static void main(String[] args) {
        String server = "localhost";
        int  port = 8000;
        
        try(Socket socket = new Socket(server, port); 
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);
        ){
        
            String userId = in.readLine();
            System.out.println("Your user id is: " + userId);

            String status = in.readLine();
            if(status.equals("READY")){
                System.out.println("Connection established! Start chatting now!");
            }
}