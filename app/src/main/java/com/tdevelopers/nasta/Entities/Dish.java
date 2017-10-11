package com.tdevelopers.nasta.Entities;

/**
 * Created by saitej dandge on 24-12-2016.
 */

public class Dish {
    public String pic;

    public String name;
    public double rating;
    public int price;
    public int category;
    public String id;

    public Dish(String pic, String name,int price) {
        this.pic = pic;
        this.name = name;
        this.price = price;

    }
}
