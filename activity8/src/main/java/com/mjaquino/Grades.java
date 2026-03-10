package com.mjaquino;

public class Grades {
    private String subjects;
    private double[]grades;

    //constructor
    public Grades(String subjects, double[] grades) {
        this.subjects = subjects;
        this.grades = new double[3];
        this.grades[0] = prelim;
        this.grades[1] = midterm;
        this.grades[2] = final;
    }

    //getter
    public String getSubjects() {
        return subjects;
    }
    public double[] getGrades() {
        return grades;
    }
    public double getGrades(int index) {
        if(index >= 0 && index < 3)
            return grades[index];
        return 0;
    }
    

    //setter
    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }
    public void setGrades(double[] grades) {
        this.grades = grades;
    }

}
