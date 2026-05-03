import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String server = "192.168.100.14";
        int port = 8000;

        try (
            Socket socket = new Socket(server, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);
        ) {
    
            System.out.println(in.readLine());

            // send client name
            String name = sc.nextLine();
            out.println(name);

            // thread to receive messages
            Thread receiveThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });

            receiveThread.start();

            // send messages
            while (true) {
                String message = sc.nextLine();
                out.println(message);
            }

        } catch (IOException e) {
            System.out.println("Unable to connect to server.");
        }
    }
}