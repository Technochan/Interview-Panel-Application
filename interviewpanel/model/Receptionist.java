package com.zsgs.chandru.interviewpanel.model;

public class Receptionist {
    private String name;
    private String emailId;
    private String phoneNo;
    private String address;
    private int id;
    private static int uniqueId;

    public int getId(){
        return id;
    }
    public void setId(){
        id = ++uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
