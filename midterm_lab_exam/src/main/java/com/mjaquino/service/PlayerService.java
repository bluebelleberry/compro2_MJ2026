package com.mjaquino.service;

import com.mjaquino.model.Account;
import com.mjaquino.model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
// handles the registration or authentication 
public class PlayerService {
    private final AccountService accountService;

    public PlayerService(AccountService accountService) {
        this.accountService = accountService;
    }

    public Player authenticatePlayer(BufferedReader in, PrintWriter out, String label) throws IOException {
        while (true) {
            // ask if sign up or log in
            out.println("");
            out.println( label + " Registration ");
            out.println("1. Sign Up");
            out.println("2. Log In");
            out.println("Choose option:");

            String option = in.readLine();
            if (option == null) return null;

            if (option.equals("1")) {
                out.println("Enter username:");
                String username = in.readLine();
                if (username == null) return null;

                out.println("Enter password:");
                String password = in.readLine();
                if (password == null) return null;

                Account account = accountService.signUp(username.trim(), password.trim());

                if (account != null) {
                    out.println("Sign up successful. Logged in as " + account.getUsername() + ", standby for a moment");
                    return new Player(account);
                } else {
                    out.println("Sign up failed. Username may already exist or input is blank.");
                }
            } else if (option.equals("2")) {
                out.println("Enter username:");
                String username = in.readLine();
                if (username == null) return null;

                out.println("Enter password:");
                String password = in.readLine();
                if (password == null) return null;

                Account account = accountService.login(username.trim(), password.trim());

                if (account != null) {
                    out.println("Login successful. Welcome back, " + account.getUsername() + ".");
                    return new Player(account);
                } else {
                    out.println("Login failed. Username or password is incorrect.");
                }
            } else {
                out.println("Invalid option. Please choose 1 or 2.");
            }
        }
    }
}