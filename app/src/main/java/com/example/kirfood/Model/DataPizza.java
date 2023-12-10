package com.example.kirfood.Model;

public class DataPizza {
    private String name;
    private String shortDes;
    private String longDes;
    private String price;
    private String promo;
    private String img;

    public DataPizza() {
    }

    public DataPizza(String name, String shortDes, String longDes, String price, String promo, String img) {
        this.name = name;
        this.shortDes = shortDes;
        this.longDes = longDes;
        this.price = price;
        this.promo = promo;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getLongDes() {
        return longDes;
    }

    public void setLongDes(String longDes) {
        this.longDes = longDes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
