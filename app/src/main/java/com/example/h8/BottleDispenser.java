package com.example.h8;

import java.util.ArrayList;
import android.view.View;

public class BottleDispenser {
    private int bottles;
    // The ArrayList for the Bottle-objects
    // It will be sized correctly later.
    private ArrayList<Bottle> BottleList = new ArrayList<Bottle>();
    private double money;

    private static BottleDispenser single_instance = null;

    private BottleDispenser() {
        bottles = 6;
        money = 0;
        BottleList.add(new Bottle("Pepsi Max", "Pepsi", 0.3,
                0.5, 1.8));
        BottleList.add(new Bottle("Pepsi Max", "Pepsi", 0.3,
                1.5, 2.2));
        BottleList.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.3,
                0.5, 2.0));
        BottleList.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.3,
                1.5, 2.5));
        BottleList.add(new Bottle("Fanta Zero", "Fanta", 0.3,
                0.5, 1.95));
        BottleList.add(new Bottle("Fanta Zero", "Fanta", 0.3,
                0.5, 1.95));
    }

    public static BottleDispenser getInstance() {
        if (single_instance == null) {
            single_instance = new BottleDispenser();
        }
        return single_instance;
    }

    /* BottleDispenser() {
        bottles = 6;
        money = 0;
        BottleList.add(new Bottle("Pepsi Max", "Pepsi", 0.3,
                0.5, 1.8));
        BottleList.add(new Bottle("Pepsi Max", "Pepsi", 0.3,
                1.5, 2.2));
        BottleList.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.3,
                0.5, 2.0));
        BottleList.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.3,
                1.5, 2.5));
        BottleList.add(new Bottle("Fanta Zero", "Fanta", 0.3,
                0.5, 1.95));
        BottleList.add(new Bottle("Fanta Zero", "Fanta", 0.3,
                0.5, 1.95));
    }*/

    public void addMoney(View v) {
        money += 1;
        System.out.println("Klink! Added more money!");
    }

    public void buyBottle(int bottle_id) {
        bottle_id -= 1;
        if (money > BottleList.get(bottle_id).getPrice()) {
            if (bottles > 0) {
                bottles -= 1;
                money -= BottleList.get(bottle_id).getPrice();
                System.out.println("KACHUNK! "+ BottleList.get(bottle_id).getName() + " came out of the dispenser!");
                BottleList.remove(bottle_id);
            } else {
                System.out.println("The machine is out of bottles!");
            }
        } else {
            System.out.println("Add money first!");
        }
    }

    public void listContents() {
        int counter = 1;
        for(Bottle object: BottleList) {
            System.out.println(counter+". Name: "+object.getName());
            System.out.println("\tSize: " + object.getSize()
                    + "\tPrice: " + object.getPrice());
            counter++;
        }
    }

    public void returnMoney() {
        System.out.print("Klink klink. Money came out! ");
        System.out.printf("You got %.2f€ back\n", money);
        money = 0;
    }
}
