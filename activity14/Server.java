import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static final int PORT = 8000;
    private static List<ClientHandler> clients = new ArrayList<>();
    public static void main(String[] args) {
        int port = 8000;

        try(ServerSocket server = new ServerSocket(port)) {
            System.out.println("Waiting for clients to be connected...");
            
  
            Socket client1 = server.accept(); //wait for client to connect
            
    
            PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));

            System.out.println("User 1 is connected.");

            out1.println("user1");

            //Wait for user/client2
            Socket client2 = server.accept(); //wait for 2nd client to connect
            //create in/out streams from this server to 
            // the client by using the same socket object that represents the accepted/connected client
            PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));

            System.out.println("User 2 connected.");
            out2.println("user2");

            System.out.println("Both can now message each other");
            out1.println("READY");
            out2.println("READY");

            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("New client connected: " + client.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(client);
                synchronized(clients) {
                    clients.add(handler);
                }
                new Thread(clientHandler).start();

                /* String user1Message = in1.readLine();
                if(user1Message == null) break;
                out2.println(user1Message);

                String user2Message = in2.readLine();
                if(user2Message == null) break;
                out1.println(user2Message);
            } 
            
            out1.close();                                                                                                                                                                                                                                                                                                           
            out2.close();
            in1.close();
            in2.close();
            client1.close();
            client2.close();  */

        } catch (IOException e) {

            System.out.println("Chat has ended...");
        }
    }
}