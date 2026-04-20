import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client{
    public static void main(String[] args) {
        String server = "localhost"; //same as 127.0.0.1
        int  port = 8000;
        
        //2: create a socket and connect to server
        try(Socket socket = new Socket(server, port); 
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);
        ){
            //identifies the first user/client that's connected
            String userId = in.readLine();

            boolean isMyTurn = userId.equals("user1");

            if(isMyTurn){
                System.out.println("--- You are User 1. You start the chat! But wait for another user.");
            }else{
                System.out.println("--- You are User 2. Wait for User 1 message!");
            }

            String status = in.readLine();
            if(status.equals("READY")){
                System.out.println("---Connection established! Start chatting now!");
            }
            

            while(true){
    
                if(isMyTurn){
                    //send message first
                    System.out.print("Your turn: ");
                    String message = sc.nextLine();
                    out.println(message);

                    if(message.equalsIgnoreCase("/quit")) break;

                    isMyTurn = false;
                    System.out.println("--- Waiting for other user...");
                }else{
                    //read other user message
                    String otherUserMessage = in.readLine();
                    if(otherUserMessage == null) 
                        break;
                    
                    System.out.println("Other user: " + otherUserMessage);
                    isMyTurn = true;

                    
                }
            }

            
        }catch(IOException e){
            System.out.println("Can't connect right now...");
        }

    }
}