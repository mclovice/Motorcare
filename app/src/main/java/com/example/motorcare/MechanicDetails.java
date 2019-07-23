package com.example.motorcare;

public class MechanicDetails {
    private String Name,Location,PhoneNumber;

    public MechanicDetails() {
    }

    public MechanicDetails(String name, String location, String phoneNumber) {
        Name = name;
        Location = location;
        PhoneNumber = phoneNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
