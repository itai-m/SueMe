package com.example.itai.sueme;

public class User {

    private int Id;
    private String Name;
    private String Email;
    private String Phonenumber;
    private String LocationLatitude;
    private String LocationLongtitude;
    private boolean Lawyer;

    public String getLocationLatitude() {
        return LocationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        LocationLatitude = locationLatitude;
    }

    public String getLocationLongtitude() {
        return LocationLongtitude;
    }

    public void setLocationLongtitude(String locationLongtitude) {
        LocationLongtitude = locationLongtitude;
    }

    public User(int id, String name, String email, String phonenumber, String locationLat, String locationLong, boolean lawyer) {
        Id = id;
        Name = name;
        Email = email;
        Phonenumber = phonenumber;
        Lawyer = lawyer;
        LocationLatitude = locationLat;
        LocationLongtitude = locationLong;
    }

    public User(int id, String name, String email, String phonenumber, String locationLat, String locationLong, int lawyer) {
        Id = id;
        Name = name;
        Email = email;
        Phonenumber = phonenumber;
        Lawyer = (lawyer == 1) ? true: false;
        LocationLatitude = locationLat;
        LocationLongtitude = locationLong;
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

    public void setLawyer(boolean lawyer) {
        Lawyer = lawyer;
    }

}
