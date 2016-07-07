package com.example.itai.sueme;

public class User {
    private String Name;
    private String Email;
    private String Phonenumber;
    private String Location;
    private boolean Lawyer;

    public User(String name, String email, String phonenumber, String location, boolean lawyer) {
        Name = name;
        Email = email;
        Phonenumber = phonenumber;
        Location = location;
        Lawyer = lawyer;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public String getLocation() {
        return Location;
    }

    public boolean isLawyer() {
        return Lawyer;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setLawyer(boolean lawyer) {
        Lawyer = lawyer;
    }
}