package com.mjaquino.model;

public class Rock extends GameMove {

    public Rock() {
        super("Rock");
    }

    @Override
    public int compare(GameMove other) {
        if (other instanceof Rock) return 0;
        if (other instanceof Scissors) return 1;
        return -1;
    }
}