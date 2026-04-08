package com.mjaquino.server;

import com.mjaquino.model.GameMove;
import com.mjaquino.model.GameSession;
import com.mjaquino.model.Match;
import com.mjaquino.model.Player;
import com.mjaquino.service.AccountService;
import com.mjaquino.service.GameService;
import com.mjaquino.service.PlayerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
// the middle man
public class Server {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        GameService gameService = new GameService();
        PlayerService playerService = new PlayerService(accountService);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for Player 1...");

            Socket socket1 = serverSocket.accept();
            PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
            // player 1
            System.out.println("Player 1 connected.");
            out1.println("Connected to server.");
            out1.println("You are Player 1. Please wait for Player 2.");

            System.out.println("Waiting for Player 2...");
            Socket socket2 = serverSocket.accept();
            PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
            // player 2
            System.out.println("Player 2 connected.");
            out2.println("Connected to server.");
            out2.println("You are Player 2.");

            out1.println("Player 2 connected. the game will begin soon");
            out2.println("Game will begin soon, please stand by for a moment");
            
            //player 1 registering
            Player player1 = playerService.authenticatePlayer(in1, out1, "Player 1");
            if (player1 == null) {
                closeAll(in1, out1, socket1, in2, out2, socket2);
                return;
            }
            // player 2 registering
            Player player2 = playerService.authenticatePlayer(in2, out2, "Player 2");
            if (player2 == null) {
                closeAll(in1, out1, socket1, in2, out2, socket2);
                return;
            }

            GameSession session = new GameSession(player1, player2);
            //the game starts
            out1.println("Welcome, " + player1.getUsername() + ".");
            out2.println("Welcome, " + player2.getUsername() + ".");
            out1.println("The game will now start.");
            out2.println("The game will now start.");

            while (session.hasMoreRounds()) {
                int round = session.getCurrentRound();

                out1.println("");
                out1.println("ROUND " + round);
                out2.println("");
                out2.println("ROUND " + round);
                //player first to choose move
                out1.println("Your turn first.");
                out2.println("Please wait. Player 1 is choosing a move.");

                out1.println("Enter move (0 = Rock, 1 = Paper, 2 = Scissors):");
                String moveInput1 = in1.readLine();
                if (moveInput1 == null) break;

                GameMove move1 = gameService.createMove(moveInput1);

                while (move1 == null) {
                    out1.println("Invalid move. Enter only 0, 1, or 2:");
                    moveInput1 = in1.readLine();
                    if (moveInput1 == null) break;
                    move1 = gameService.createMove(moveInput1);
                }

                if (move1 == null) break;

                out1.println("Move submitted. Waiting for Player 2.");
                // player 2 will now to choose move
                out2.println("Player 1 has chosen. It is now your turn.");

                out2.println("Enter move (0 = Rock, 1 = Paper, 2 = Scissors):");
                String moveInput2 = in2.readLine();
                if (moveInput2 == null) break;

                GameMove move2 = gameService.createMove(moveInput2);

                while (move2 == null) {
                    out2.println("Invalid move. Enter only 0, 1, or 2:");
                    moveInput2 = in2.readLine();
                    if (moveInput2 == null) break;
                    move2 = gameService.createMove(moveInput2);
                }

                if (move2 == null) break;
                // show round score results
                String roundDetails = gameService.buildRoundDetails(session, player1, player2, move1, move2);

                out1.println("");
                out1.println(roundDetails);

                out2.println("");
                out2.println(roundDetails);
            }
            // get round scores 
            int player1Score = player1.getScore();
            int player2Score = player2.getScore();

            // set wins/losses based on round results
            player1.getAccount().setWins(player1Score);
            player1.getAccount().setLosses(player2Score);

            player2.getAccount().setWins(player2Score);
            player2.getAccount().setLosses(player1Score);

            String winner = session.getFinalWinner();
            //updating results 
            Match finishedMatch = new Match(player1, player2, winner);

            accountService.updateAccount(finishedMatch.getPlayer1().getAccount());
            accountService.updateAccount(finishedMatch.getPlayer2().getAccount());

            // show leaderboard
            String leaderboard = session.getLeaderboard();

            out1.println("");
            out1.println(leaderboard);
            out1.println("Recorded match result: " + player1.getAccount().getWins() + " wins, " + player1.getAccount().getLosses() + " losses, " + "win rate = " + player1.getAccount().getWinRate());
            out1.println("The game is finished, congratulations to the players, you did a great job!!");

            out2.println("");
            out2.println(leaderboard);
            out2.println("Recorded match result: " + player2.getAccount().getWins() + " wins, " + player2.getAccount().getLosses() + " losses, " + "win rate = " + player2.getAccount().getWinRate());
            
            out2.println("The game is finished, congratulations to the players, you did a great job!!");
            closeAll(in1, out1, socket1, in2, out2, socket2);
            
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
    // closing all conections
    private static void closeAll(BufferedReader in1, PrintWriter out1, Socket socket1, BufferedReader in2, PrintWriter out2, Socket socket2) {
        try {
            if (in1 != null) in1.close();
            if (out1 != null) out1.close();
            if (socket1 != null) socket1.close();

            if (in2 != null) in2.close();
            if (out2 != null) out2.close();
            if (socket2 != null) socket2.close();
        } catch (IOException e) {
            System.out.println("Error while closing connections.");
        }
    }
}