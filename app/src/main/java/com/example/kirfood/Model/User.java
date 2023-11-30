package com.example.kirfood.Model;

public class User {
    private String Nama,Password;
    public User(){
        Nama = "";
        Password = "";
    }
    public String getNama(){
        return Nama;
    }
    public void setNama(String nama){
        Nama = nama;
    }
    public String getPassword(){
        return Password;
    }
    public void setPassword(String password){
        Password = password;
    }
}
