package com.mjaquino.model;
// the player during the game
public class Player {
    //data feilds 
    private Account account;
    private int score;
    private GameMove currentMove;

    public Player(Account account) {
        this.account = account;
        this.score = 0;
    }
    
    //getters and setters 
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getUsername() {
        return account.getUsername();
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public GameMove getCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(GameMove currentMove) {
        this.currentMove = currentMove;
    }

    //method
    public void incrementScore() {
        score++;
    }

}
