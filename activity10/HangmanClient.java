import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class HangmanClient{
    public static void main(String[] args){
        String server="192.168.100.14"; //wifi ip address
        int port=8000;

        try(
            Socket socket=new Socket(server,port);
            PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc=new Scanner(System.in);
        ){

            System.out.println("Connected to the server.");

            while(true){

                String message=in.readLine();

                if(message==null){
                    break;
                }

                if(message.equals("END")){
                    break;
                }

                System.out.println(message);

                if(message.equals("Enter choice:") ||message.equals("Enter username:") ||message.equals("Enter password:") ||message.equals("Create username:") ||message.equals("Create password:") ||message.equals("Guess a letter:") ||message.equals("Play again? (y/n)") ||message.equals("Enter y or n only:")){
                    String reply=sc.nextLine();
                    out.println(reply);
                }
            }

        }catch(IOException e){
            System.out.println("Can't connect right now...");
        }
    }
}