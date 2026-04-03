package com.mjaquino.model;

public class Player {
    //data feilds 
    private String name;
    private int score;
    private GameMove currentMove;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }
    
    //getters and setters 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
