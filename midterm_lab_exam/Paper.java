package com.mjaquino.model;
// concrete subclass
public class Paper extends GameMove {
    public Paper() {
        super("Paper");
    }

    @Override
    public int compare(GameMove other) {
        if (other instanceof Paper) return 0;
        if (other instanceof Rock) return 1;
        return -1;
    }
}