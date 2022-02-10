package com.techelevator;

import java.util.Map;

public class Product {

    //initializing variables get & set
    private String name;
    public String getName(){return name;}

    private double price;
    public double getPrice(){
        return price;
    }

    private String type;
    public String getType(){
        return type;
    }

    //what should our initial quantity be?
    private int quantity;
    public int getQuantity(){
        return quantity;
    }

    //constructors
    public Product(String name, double price, String type){
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = 5;
    }

    //methods

    //possibly a toString
    @Override
    public String toString(){
        return this.name + " " + this.price+ " " + this.type;
    }
}
