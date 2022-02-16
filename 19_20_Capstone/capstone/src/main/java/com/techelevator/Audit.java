package com.techelevator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Audit {

    private String dateAndTime;
    public String getDateAndTime() {
        return dateAndTime;
    }

    private String action;
    public String getAction() {
        return action;
    }
    public void setAction(String action){
        this.action = action;
    }

    private String deposit;
    public String getDeposit(){
        return deposit;
    }

    private String balance;
    public String getBalance(){
        return  balance;
    }

    //constructors
    public Audit(String action, String deposit, String balance) {
        findDateAndTime();
        this.action = action;
        this.deposit = deposit;
        this.balance = balance;
    }

    public Audit (){

    }

   //formatting our date and time
    public void findDateAndTime(){ //unit test WIP
        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh.mm.ss aa");
        dateAndTime = dateFormat2.format(new Date()).toString();
    }


    @Override
    public String toString() {
        //add dollar signs and stuff here
        return dateAndTime + " " + action + " $" + deposit + " $" + balance;
    }
}
