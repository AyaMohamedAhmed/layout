package com.example.myapplication;

public class User {
    private String phone;
    private String message;
    public User(String phone,String message){
        this.phone=phone;
        this.message=message;
    }
    public  String getPhone(){
        return  phone;
    }
    public  void setPhone(String phone){
        this.phone=phone;
    }
    public String getMessage(){
        return message;
    }
    public  void setMessage(String message){
        this.message=message;
    }

}
