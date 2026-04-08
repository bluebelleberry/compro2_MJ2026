package com.mjaquino.model;

//controls one whole game, the 10 round match
public class GameSession {
    //data feilds
    private final Player player1;
    private final Player player2;
    private final int maxRounds;
    private int currentRound;

    //constructor
    public GameSession(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.maxRounds = 10;
        this.currentRound = 1;
    }

    //getters
    public int getCurrentRound() {
        return currentRound;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public boolean hasMoreRounds() {
        return currentRound <= maxRounds;
    }

    //comparing players move
    public String playRound(GameMove move1, GameMove move2) {
        player1.setCurrentMove(move1);
        player2.setCurrentMove(move2);

        int result = move1.compare(move2);
        String roundMessage;
        //who ever win earn 1 point
        if (result == 1) {
            player1.incrementScore();
            roundMessage = "Round " + currentRound + ": " + player1.getUsername() + " wins this round!\n";
        } else if (result == -1) {
            player2.incrementScore();
            roundMessage = "Round " + currentRound + ": " + player2.getUsername() + " wins this round!\n";
        } else {
            roundMessage = "Round " + currentRound + ": It's a tie!\n";
        }

        currentRound++;
        return roundMessage;
    }

    // score for each round
    public String getScoreBoard() {
        return """
               \n
               SCOREBOARD
               %s: %d
               %s: %d
               """.formatted(
                player1.getUsername(), player1.getScore(),
                player2.getUsername(), player2.getScore()
        );
    }
    
    // identifying the winner
    public String getFinalWinner() {
        if (player1.getScore() > player2.getScore()) return player1.getUsername();
        if (player2.getScore() > player1.getScore()) return player2.getUsername();
        return "Draw";
    }

    // leaderboard 
    public String getLeaderboard() {
        String firstName;
        int firstScore;
        String secondName;
        int secondScore;

        // comparinf total score of both players
        if (player1.getScore() >= player2.getScore()) {
            firstName = player1.getUsername();
            firstScore = player1.getScore();
            secondName = player2.getUsername();
            secondScore = player2.getScore();
        } else {
            firstName = player2.getUsername();
            firstScore = player2.getScore();
            secondName = player1.getUsername();
            secondScore = player1.getScore();
        }

        return """
               LEADERBOARD
               1. %s - %d wins
               2. %s - %d wins
               Overall Winner: %s
               """.formatted(firstName, firstScore, secondName, secondScore, getFinalWinner());
    }
}