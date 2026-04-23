package com.mjaquino.model;
//abstract for all moves
public abstract class GameMove {
    //data feilds
    private String moveName;

    //constructor
    public GameMove(String moveName) {
        this.moveName = moveName;
    }

    //getter
    public String getMoveName() {
        return moveName;
    }

    //setter
    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }
    //abstract method
    public abstract int compare(GameMove other);
}