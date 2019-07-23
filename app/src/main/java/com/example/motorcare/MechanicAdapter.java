package com.example.motorcare;

public class MechanicAdapter {
    String GarageName, Location, Phone;

    public MechanicAdapter() {
        // A default constructor
    }

    public MechanicAdapter(String garageName, String location, String phone) {
        GarageName = garageName;
        Location = location;
        Phone = phone;
    }

    public String getGarageName() {
        return GarageName;
    }

    public void setGarageName(String garageName) {
        GarageName = garageName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}