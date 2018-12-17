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

import java.util.ArrayList;

public class BottleDispenser {

    private int bottles;
    public double money;
    private ArrayList<Bottle> bottle_array = new ArrayList();
    private static BottleDispenser bd = new BottleDispenser();

    public static BottleDispenser getInstance() {
        return bd;
    }

    private BottleDispenser() {
        bottles = 6;
        money = 0;
        //Koska pulloja on nyt erilaisia, täytyy ne tehdä yksitellen ja lisätä listaan.
        Bottle pullo1 = new Bottle("Pepsi Max", "Pepsi", 1.8, 0.5);
        bottle_array.add(pullo1);
        Bottle pullo2 = new Bottle("Pepsi Max", "Pepsi", 2.2, 1.5);
        bottle_array.add(pullo2);
        Bottle pullo3 = new Bottle("Coca-Cola Zero", "Coca-Cola", 2.0, 0.5);
        bottle_array.add(pullo3);
        Bottle pullo4 = new Bottle("Coca-Cola Zero", "Coca-Cola", 2.5, 1.5);
        bottle_array.add(pullo4);
        Bottle pullo5 = new Bottle("Fanta Zero", "Fanta", 1.95, 0.5);
        bottle_array.add(pullo5);
        Bottle pullo6 = new Bottle("Fanta Zero", "Fanta", 1.95, 0.5);
        bottle_array.add(pullo6);


    }

    public void addMoney(double amount) {
        money += amount;

    }



    //Otetaan parametrina käyttäjän haluama pullo, ja muistetaan että käyttäjälle
    //näkyneessä listassa pullojen lista alkoi numerosta 1 eikä 0 niin kuin
    //arrayListissä.
    public void buyBottle(int indeksi) {
        if ( money < bottle_array.get(indeksi-1).price ) {

        }

        else if ( bottles <= 0 ) {

        }

        else {
            bottles--;
            money = money - bottle_array.get(indeksi-1).price;

            bottle_array.remove(indeksi-1);
        }

    }

    public String buyBottleById(String id) {
        for (int i = 0; i<bottle_array.size(); i++) {
            if (bottle_array.get(i).id.equals(id)) {
                if (money < bottle_array.get(i).price) {
                    return "Rahat eivät riitä.";
                } else {
                    money = money - bottle_array.get(i).price;

                    bottle_array.remove(i);
                    return "Ostaminen onnistui.";
                }

            }


        }


        return "Koneessa ei ole enää tuota pulloa";

    }

    public void returnMoney() {
        //Formatoidaan double kahden desimaalin tarkkuudelle, että ei tule
        //liian tarkkoja tuloksia vahingossa.

        money = 0;

    }
    /* Listataan pullot ArrayListin avulla. */
    public void listBottles() {
        for ( int i = 1; i <= bottle_array.size(); i++ ) {
            System.out.println(i + ". Nimi: " + bottle_array.get(i-1).name);
            System.out.println("\tKoko: " + bottle_array.get(i-1).size +
                    "\tHinta: " + bottle_array.get(i-1).price);
        }
    }
}