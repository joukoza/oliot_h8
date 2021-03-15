package com.example.h8;

import java.util.ArrayList;

public class BottleDispenser {
    private int bottles;
    // The ArrayList for the Bottle-objects
    // It will be sized correctly later.
    private ArrayList<Bottle> BottleList = new ArrayList<Bottle>();
    private double money;
    private String receipt = "";

    private static BottleDispenser single_instance = null;

    private BottleDispenser() {
        bottles = 6;
        money = 0;
        BottleList.add(new Bottle("Pepsi", "Pepsi", 0.3,
                0.5, 1.5));
        BottleList.add(new Bottle("Pepsi", "Pepsi", 0.3,
                1.5, 2.0));
        BottleList.add(new Bottle("Coca-Cola", "Coca-Cola", 0.3,
                0.5, 1.5));
        BottleList.add(new Bottle("Coca-Cola", "Coca-Cola", 0.3,
                1.5, 2.0));
        BottleList.add(new Bottle("Fanta", "Fanta", 0.3,
                0.5, 1.5));
        BottleList.add(new Bottle("Fanta", "Fanta", 0.3,
                1.5, 2.0));
    }

    public static BottleDispenser getInstance() {
        if (single_instance == null) {
            single_instance = new BottleDispenser();
        }
        return single_instance;
    }

    public void addMoney(int value) {
        money += value;
    }

    public String buyBottle(String bottleName) {
        boolean bottleFound = false;
        String returnMessage = "";
        String[] bottleStuff = bottleName.split(" ");
        String bottleManufacturer = bottleStuff[0];
        double bottleSize = Double.parseDouble(bottleStuff[1]);
        for(Bottle object: BottleList){
            if ((object.getManufacturer().equals(bottleManufacturer))){
                if ((object.getSize() == bottleSize)){
                    if (money >= object.getPrice()) {
                        BottleList.remove(object);
                        money -= object.getPrice();
                        bottles -= 1;
                        returnMessage = "KACHUNK! " + bottleManufacturer + " " +
                                bottleSize + " came out of the dispenser!";
                        bottleFound = true;
                        receipt = "RECEIPT\nLast bought item:\n" +
                                bottleManufacturer + " Size: " + bottleSize + " Price: " +
                                object.getPrice();
                        break;
                    }
                }
            }
        }
        if (bottleFound == false){
            returnMessage = "Bottle not found, or insufficient funds.";
        }
        return returnMessage;
    }

    public String listContents() {
        int counter = 1;
        String bottles = "";
        for(Bottle object: BottleList) {
            /*System.out.println(counter+". Name: "+object.getName());
            System.out.println("\tSize: " + object.getSize()
                    + "\tPrice: " + object.getPrice());*/
            bottles = bottles.concat(Integer.toString(counter) +  ". " + object.getName());
            bottles = bottles.concat(" Size: "+Double.toString(object.getSize()));
            bottles = bottles.concat(" Price: "+Double.toString(object.getPrice())+ "\n");
            counter++;
            System.out.println(bottles);
        }
        return bottles;
    }

    public String returnMoney() {

        String returnMessage = "Klink klink. Money came out! " +
                "You got " + money + " € back.";
        money = 0;
        return returnMessage;
    }

    public double getMoney(){
        return money;
    }

    public String getReceipt(){
        return receipt;
    }
}
