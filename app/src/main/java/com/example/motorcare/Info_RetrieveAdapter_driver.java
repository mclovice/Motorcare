package com.example.motorcare;

public class Info_RetrieveAdapter_driver {
    String Theme, CarMaintenanceInfo;

    public Info_RetrieveAdapter_driver() {

    }

    public Info_RetrieveAdapter_driver(String Info_theme, String Full_info) {
        Theme = Info_theme;
        CarMaintenanceInfo = Full_info;

    }

    public String getTheme() {
        return Theme;
    }

    public void setTheme(String Info_theme) {
        Theme = Info_theme;
    }

    public String getCarMaintenanceInfo() {
        return CarMaintenanceInfo;
    }

    public void setCarMaintenanceInfo(String Full_info) {
        CarMaintenanceInfo = Full_info;
    }
}
