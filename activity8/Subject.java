package com.mjaquino.model;

public class Subject {
    private String name;
    private int prelim;
    private int midterm;
    private int finals;

    public Subject(String name) {
        this.name = name;
        this.prelim = 0;
        this.midterm = 0;
        this.finals = 0;
    }

    public String getName() {
        return name;
    }

    public int getPrelim() {
        return prelim;
    }

    public int getMidterm() {
        return midterm;
    }

    public int getFinals() {
        return finals;
    }

    public void setGrades(int prelim, int midterm, int finals) {
        this.prelim = prelim;
        this.midterm = midterm;
        this.finals = finals;
    }
}