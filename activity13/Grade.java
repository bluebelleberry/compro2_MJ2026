package com.mjaquino.model;

public class Grade {
    //data feilds
    private String subject;
    private int prelim;
    private int midterm;
    private int fin;

    //contructor
    public Grade(String subject, int prelim, int midterm, int fin) {
        this.subject = subject;
        this.prelim = prelim;
        this.midterm = midterm;
        this.fin = fin;
    }

    //getter
    public String getSubject() {
        return subject;
    }

    public int getPrelim() {
        return prelim;
    }

    public int getMidterm() {
        return midterm;
    }

    public int getFin() {
        return fin;
    }

    //setter
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPrelim(int prelim) {
        this.prelim = prelim;
    }

    public void setMidterm(int midterm) {
        this.midterm = midterm;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }
}