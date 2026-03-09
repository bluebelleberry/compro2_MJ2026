package com.mjaquino;

public class Person {
    private String firstName;
    private String lastnName;
    private int age;
    private String emailAddress;
    private String phoneNumber;
    private String dateOfBirth;
    private String homeAddress;
    private boolean isEmployed;
    private String nationality;
    private String gender;

    //constructor
    public Person(String firstName, String lastnName, int age, String emailAddress, String phoneNumber, String dateOfBirth, String homeAddress, boolean isEmployed, String nationality, String gender) {
        this.firstName = firstName;
        this.lastnName = lastnName;
        this.age = age;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.homeAddress = homeAddress;
        this.isEmployed = isEmployed;
        this.nationality = nationality;
        this.gender = gender;
    }

    //getter
    public String getFirstName() {
        return firstName;
    }
    public String getLastnName() {
        return lastnName;
    }
    public int getAge() {
        return age;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getHomeAddress() {
        return homeAddress;
    }
    public boolean getIsEmployed() {
        return isEmployed;
    }
    public String getNationality() {
        return nationality;
    }
    public String getGender() {
        return gender;
    }

    //setter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastnName(String lastnName) {
        this.lastnName = lastnName;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    public void setIsEmployed(boolean isEmployed) {
        this.isEmployed = isEmployed;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString(){
        return String.format("""
                First Name: %s
                Last Name: %s
                Age: %d
                Email Address: %s
                Phone Number: %s
                Date of Birth: %s
                Home Address: %s
                Is Employed?: %b
                Nationality: %s
                Gender: %s
                """, firstName, lastnName, age, emailAddress, phoneNumber, dateOfBirth, homeAddress, isEmployed, nationality, gender);
    }



    
}
