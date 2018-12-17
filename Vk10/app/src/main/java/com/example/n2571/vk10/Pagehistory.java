package com.example.n2571.vk10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Pagehistory {
    static ArrayList<Page> history;
    static Pagehistory pagehistory = new Pagehistory();
    static ListIterator<Page> iterator;


    public static Pagehistory getinstance() {
        return pagehistory;
    }

    private Pagehistory() {
        this.history = new ArrayList<Page>();
        this.iterator = this.history.listIterator();
    }

    public static String getNext() {
        if ( iterator.hasPrevious() ) {
            return iterator.previous().getUrl();
        }
        return null;
    }

    public static String getPrevious() {
        if ( iterator.hasNext() ) {
            System.out.println(iterator.next().getUrl());
            return iterator.next().getUrl();
        }
        return null;
    }

    public static void addPage(String url) {
        Page newPage = new Page(url);
        history.add(0, newPage);
    }

    public static void print() {
        for (int i = 0; i < history.size(); i++) {
            System.out.println(history.get(i).getUrl());
        }
    }




}
