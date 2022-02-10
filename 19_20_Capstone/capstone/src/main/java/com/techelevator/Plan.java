package com.techelevator;

public class Plan {

    //Story -1 --> Update Inventory
        //standard inventory is 5 for each item
        //each item has a name, price, and type(beverage, candy, chips, gum) --> class Products

    //Story -2 --> Display Items Available
        //display as: name, slot identifier, and purchase price
        //if (item is sold out) {display item as SOLD OUT}

    //Story -3 --> Deposit Money
        //while customer is in purchase menu
        //Can only deposit dollars (no coins)
        //with each deposit, balance is updated and displayed

    //Story - 4 --> Purchasing Item
        //if balance is 0 when they select a product, they are given an error message saying they must deposit money before making selection
        //When they select item for purchase:
            //1) customer's current balance is updated based on the item cost
            //2) inventory and balance of the vending machine are updated
            //3) item dispensed and the user receives a message based on the item type:
                //--> chips will be "Crunch Crunch, Yum!"
                //--> candy "Munch Munch, Yum!"
                //--> drinks "Glug Glug, Yum!"
                //--> gum "Chew Chew, Yum!"
            //4) customer is returned to purchase menu
        //IF SOLD OUT or COSTS MORE THAN BALANCE --> they are given error message and returned to the purchase menu --> class MENU

    //Story - 5 --> Return Change
        //if customer is on purchase menu:
            //chose Finish Transaction --> then customer gets message of their change in a double value, then return them to the main menu

    //Story - 6 --> Audit File (probably needs to make a file called audit file)
        //if a customer has fed in money:
            //audit file should contain entry called "FEED MONEY" (date, time, amount, new balance)
            //if item was purchased: display entry for purchase with (date, time, item name, item slot, item price, remaining balance)
            //if transaction was completed: file should display label "GIVE CHANGE" (date, time, amount of change, remaining balance $0)
}
