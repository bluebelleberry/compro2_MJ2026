import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String clientName;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("Enter your name:");
            clientName = in.readLine();

            System.out.println(clientName + " joined the chat.");
            Server.broadcast(clientName + " has joined the chat", this);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(clientName + ": " + message);
                Server.broadcast(clientName + ": " + message, this);
            }

        } catch (IOException e) {
            System.out.println(clientName + " disconnected.");
        } finally {
            Server.removeClient(this);
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}