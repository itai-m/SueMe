package com.example.itai.sueme;

public class User {

    private int Id;
    private String Name;
    private String Email;
    private String Phonenumber;
    private String Location;
    private boolean Lawyer;

    public User(int id, String name, String email, String phonenumber, String location, boolean lawyer) {
        Id = id;
        Name = name;
        Email = email;
        Phonenumber = phonenumber;
        Location = location;
        Lawyer = lawyer;
    }

    public User(int id, String name, String email, String phonenumber, String location, int lawyer) {
        Id = id;
        Name = name;
        Email = email;
        Phonenumber = phonenumber;
        Location = location;
        Lawyer = (lawyer == 1) ? true: false;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
