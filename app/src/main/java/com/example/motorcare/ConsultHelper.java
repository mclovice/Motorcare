package com.example.motorcare;

public class ConsultHelper {
    public  String messageBody;
    public  String Address;
    public String Period;
    public int Id;

    public ConsultHelper() {
    }

    public ConsultHelper(String messageBody, String address, String period,int id) {
        this.messageBody = messageBody;
        Address = address;
        Period = period;
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }
}
