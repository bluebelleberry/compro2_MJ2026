package com.mjaquino.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
// player side
public class Client {
    public static void main(String[] args) {
        String server = "192.168.110.255";
        int port = 8000;

        try (
                Socket socket = new Socket(server, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)
        ) {
            // reads messages from server
            String line;

            while ((line = in.readLine()) != null) {
                // display server message
                System.out.println(line);

                // send user input only when needed
                if (needsInput(line)) {
                    String input = scanner.nextLine();
                    out.println(input);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to connect to server.");
        }
    }

    private static boolean needsInput(String line) {
        // checking if server ask for the inputs needed
        return line.equals("Choose option:")
                || line.equals("Enter username:")
                || line.equals("Enter password:")
                || line.equals("Enter move (0 = Rock, 1 = Paper, 2 = Scissors):")
                || line.equals("Invalid move. Enter only 0, 1, or 2:");
    }
}