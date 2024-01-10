package com.example.kirfood.Model;

public class DataCart {
    public String name;
    public String price;

    public DataCart(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public DataCart() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
