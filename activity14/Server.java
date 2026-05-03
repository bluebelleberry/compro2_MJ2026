import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 8000;
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("New client connected: " + client.getInetAddress().getHostAddress());

                ClientHandler handler = new ClientHandler(client);
                
                synchronized (clients) {
                    clients.add(handler);
                }

                new Thread(handler).start();
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    // show message to all clients except sender
    public static void broadcast(String message, ClientHandler sender) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != sender) {
                    client.sendMessage(message);
                }
            }
        }
    }

    // remove disconnected client
    public static void removeClient(ClientHandler client) {
        synchronized (clients) {
            clients.remove(client);
        }
    }
}