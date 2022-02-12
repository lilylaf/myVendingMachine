package com.techelevator;

import com.sun.source.tree.Tree;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    private BigDecimal userBalance = BigDecimal.valueOf(0); //will be updated when depositing, purchasing, and getting change
    public BigDecimal getUserBalance() {
        return userBalance;
    }

    private List<Audit> auditList = new ArrayList();
    public void addToAuditList(Audit a){
        auditList.add(a);
    }

    public String returnAudits (){
        String strReturnAudits = "";
        for(Audit a : auditList){
            strReturnAudits += a + "\n";
        }
        return strReturnAudits;
    }

    //pass items for Audit as strings
    public String getDepositMoney(String userChoice){
        int intDeposit = Integer.valueOf(userChoice);
        return String.format("%.2f", BigDecimal.valueOf(intDeposit));
    }

    public String getNameAndButton(String userChoice){
        return this.buttonSelection(userChoice).getName() + " " + userChoice.toUpperCase();
    }

    public String getPriceOfProduct(String userChoice) {
        return String.valueOf(this.buttonSelection(userChoice).getPrice());
    }

    //method user depositing money into vending machine
    public void depositMoney(String userChoice){
        int intBalance = 0;
        try {
            int intDeposit = Integer.valueOf(userChoice);
            intBalance +=intDeposit;
            userBalance = BigDecimal.valueOf(intBalance);
            System.out.println("Your total balance is:  $" + String.format("%.2f", userBalance));
            this.addToAuditList(new Audit("FEED MONEY:", getDepositMoney(userChoice), String.format("%.2f", userBalance)));
        }catch (NumberFormatException e) {
            System.out.println(System.lineSeparator() + "*** " + userChoice + " is not a valid option ***" + System.lineSeparator());
            System.out.println("Your total balance is:  $" + String.format("%.2f", userBalance));
        }
    }
    //method checking user button selection
    public Product buttonSelection(String userInput){
        try {
            return mapOfProducts.get(userInput.toUpperCase());
        } catch (Exception e) {
            //e.printStackTrace();
            e.getMessage();
            System.out.println("***not valid choice selection***");
        }
        return null;
    }
    //method selecting item
    public void selectProduct(String userInput){ //look at this later
        //remove loop - call buttonSelection.get etc...
        if (buttonSelection(userInput).getQuantity() == 0){  //product sold out
                System.out.println("SOLD OUT");
        } else if (buttonSelection((userInput)).getPrice().compareTo(userBalance) == -1 ||
                buttonSelection((userInput)).getPrice().compareTo(userBalance) == 0){ //if price of product is <= userBalance
                completeAPurchase(userInput);
        } else if (buttonSelection(userInput).getPrice().compareTo(userBalance) == 1){ //price of product is > userBalance
                System.out.println("Please deposit more money, or select a different product");
        }
    }

    public void completeAPurchase(String userInput){
        //update customer balance after purchase is selected
        userBalance =  userBalance.subtract(buttonSelection(userInput).getPrice());
        this.addToAuditList(new Audit(getNameAndButton(userInput), getPriceOfProduct(userInput),getUserBalance().toString()));

        //adjust inventory of item
        buttonSelection(userInput).setQuantity(buttonSelection(userInput).getQuantity() - 1);
        //dispense item and display our cheesy message
        System.out.println(buttonSelection(userInput).getName() + " has been dispensed.");
        if(buttonSelection(userInput).getType().equals("Chip")){ //candy drink and gum
            System.out.println("Crunch Crunch, Yum!");
        } else if(buttonSelection(userInput).getType().equals("Candy")){
            System.out.println("Munch Munch, Yum!");
        } else if(buttonSelection(userInput).getType().equals("Drink")){
            System.out.println("Glug Glug, Yum!");
        } else if(buttonSelection(userInput).getType().equals("Gum")){
            System.out.println("Chew Chew, Yum!");
        }
        System.out.format("Your current balance is: $" + String.format("%.2f", userBalance) + System.lineSeparator());
    }

    public void finishTransaction(){
        //when customer selects finish, print "Thank you for your purchase. Your change is $" +  userBalance
        System.out.println("Thank you for your business. Your change due $" + String.format("%.2f", userBalance));
        this.addToAuditList(new Audit("GIVE CHANGE:", getUserBalance().toString(),  "0.00"));
        BigDecimal tempBalance = userBalance;

        BigDecimal removeQuarters = tempBalance.divide(BigDecimal.valueOf(.25));
        BigDecimal quartersTotal = removeQuarters.setScale(0, RoundingMode.DOWN);
        tempBalance = tempBalance.subtract(quartersTotal.multiply(BigDecimal.valueOf(.25)));

        BigDecimal removeDimes = tempBalance.divide(BigDecimal.valueOf(.10));
        BigDecimal dimesTotal = removeDimes.setScale(0, RoundingMode.DOWN);
        tempBalance = tempBalance.subtract(dimesTotal.multiply(BigDecimal.valueOf(.10)));

        BigDecimal totalNickles = tempBalance.divide(BigDecimal.valueOf(.05));

        System.out.println("quarters: " + quartersTotal  + ", dimes: " + dimesTotal + ", nickles: " + totalNickles);
        userBalance = BigDecimal.valueOf(0.0);
    }

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
