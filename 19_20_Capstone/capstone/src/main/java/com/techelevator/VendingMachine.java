package com.techelevator;

import com.sun.source.tree.Tree;

import java.util.*;

public class VendingMachine {

    //ArrayList of products
    private Map<String, Product> mapOfProducts = new HashMap();
    public Map<String, Product> getMapOfProducts(){
        return mapOfProducts;
    }
    public void addToMap(String vendingButton, Product p){
        mapOfProducts.put(vendingButton, p);
    }


    //methods

    //method to adjust inventory number

    //our vending machine has buttons, but our product has a button value assigned
    @Override
    public String toString(){ //googled how to format a string in java to get the formatting for our map
        String s = "";
        Map<String, Product> sortedMap = new TreeMap<>(mapOfProducts);
        for (Map.Entry<String, Product> entry : sortedMap.entrySet()){
            s += ( entry.getKey() + " " + String.format("%" + -20 + "s", entry.getValue().getName()) + " $" + String.format("%.2f", entry.getValue().getPrice())) + System.lineSeparator();
            if(entry.getValue().getQuantity() <= 0){
                s += entry.getValue().getName() + " SOLD OUT " + System.lineSeparator();
            }
        }
        return s;
    }
}
