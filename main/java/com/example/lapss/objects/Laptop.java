package com.example.lapss.objects;

public class Laptop {
    private int id;
    private float price;
    private String name;
    private String img;
    private String company;
    
    public Laptop(int id, float price, String name, String img, String company) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.img = img;
        this.company = company;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
