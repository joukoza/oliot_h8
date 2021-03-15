package com.example.h8;

public class Bottle {

    private String name;
    private String manufacturer;
    private double total_energy;
    private double size;
    private double price;

    public Bottle(){
        name = "Pepsi";
        manufacturer = "Pepsi";
        total_energy = 0.3;
        size = 0.5;
        price = 1.80;
    }
    public Bottle(String nam, String manuf, double totE, double siz, double pri){
        name = nam;
        manufacturer = manuf;
        total_energy = totE;
        size = siz;
        price = pri;
    }
    public String getName(){
        return name;
    }
    public String getManufacturer(){
        return manufacturer;
    }
    public double getEnergy(){
        return total_energy;
    }
    public double getSize(){
        return size;
    }
    public double getPrice(){
        return price;
    }

}
