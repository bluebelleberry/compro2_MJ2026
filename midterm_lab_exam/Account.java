package com.mjaquino.model;
// user's account data
public class Account {
    // data feilds
    private String username;
    private String password;
    private int wins;
    private int losses;
    private double winRate;

    //constructor
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.wins = 0;
        this.losses = 0;
        updateWinRate();
    }

    public Account(String username, String password, int wins, int losses) {
        this.username = username;
        this.password = password;
        this.wins = wins;
        this.losses = losses;
        updateWinRate();
    }

    //getter & setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
    this.wins = wins;
    updateWinRate();
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
    this.losses = losses;
    updateWinRate();
    }

    public double getWinRate() {
        return winRate;
    }

    //methods for win rate
    private void updateWinRate() {
        int totalGames = wins + losses;
        if (totalGames == 0) {
            winRate = 0.0;
        } else {
            winRate = (double) wins * 100/ totalGames;
        }
    }
}