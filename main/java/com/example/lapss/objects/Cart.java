package com.example.lapss.objects;

public class Cart {
    private int idUer;
    private int idProduct;


    public Cart(int idUer,int idProduct) {
        this.idUer = idUer;
        this.idProduct = idProduct;


    }
    public int getIdUer() {
        return idUer;
    }

    public void setIdUer(int idUer) {
        this.idUer = idUer;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
