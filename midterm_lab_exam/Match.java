package com.mjaquino.model;
//one completed match, it models the finished game
public class Match {
    //data feilds
    private Player player1;
    private Player player2;
    private String winner;

    //constructor
    public Match(Player player1, Player player2, String winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    //getters
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getWinner() {
        return winner;
    }
}