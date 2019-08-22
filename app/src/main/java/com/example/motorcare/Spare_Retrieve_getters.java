package com.example.motorcare;

public class Spare_Retrieve_getters {

    String SparePartName, Model, Cost;
    public Spare_Retrieve_getters() {
    }

    public Spare_Retrieve_getters(String sparePartName, String model, String cost) {
        SparePartName = sparePartName;
        Model = model;
        Cost = cost;
    }

    public String getSparePartName() {
        return SparePartName;
    }

    public void setSparePartName(String sparePartName) {
        SparePartName = sparePartName;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }
}
