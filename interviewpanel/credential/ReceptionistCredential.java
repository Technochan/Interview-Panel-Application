package com.zsgs.chandru.interviewpanel.credential;

public class ReceptionistCredential {
    private int id ;
    private String password;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
}
