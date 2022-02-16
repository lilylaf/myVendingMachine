package com.techelevator;

import java.math.BigDecimal;
import java.util.Map;

public class Product {

    private String name;
    public String getName(){return name;}

    private BigDecimal price;
    public BigDecimal getPrice(){
        return price;
    }

    private String type;
    public String getType(){
        return type;
    }

    private int quantity;
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    //constructor
    public Product(String name, BigDecimal price, String type){
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = 5;
    }


    //possibly a toString
    @Override
    public String toString(){
        return this.name + " " + this.price+ " " + this.type;
    }
}
