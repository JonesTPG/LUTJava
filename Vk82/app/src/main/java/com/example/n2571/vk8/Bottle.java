package com.example.n2571.vk8;

/*
 * Tekijä: Joonas Ryynänen
Opiskelijanumero: 0507674'
Päivämäärä: 9.9.2018
 */

/**
 *
 * @author joonas
 */

//Luokalle on määritelty getterimetodit, vaikka niitä nyt tässä tehtävässä ei välttämättä
//tarvitsekaan.
public class Bottle {
    String name;
    String manufacturer;
    double total_energy;
    double size = 0.5;
    double price = 1.80;
    String id;


    public Bottle() {
        name = "Pepsi Max";
        manufacturer = "Pepsi";
        total_energy = 0.3;
    }

    //Tässä toinen konstruktori, jonka avulla voidaan määrätä joka pullolle omat tietonsa.
    public Bottle(String name, String manufacturer, double price, double size) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.size = size;
        this.id = name + size;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getTotal_energy() {
        return total_energy;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }





}