package com.tdevelopers.nasta.Entities;

/**
 * Created by Programmer on 10-10-2017.
 */

public class JSONdata
{
    public String vendor;
    public String category;
    public String foodName;
    public String foodImageUrl;
    public int price;

    public JSONdata(String vendor,String category,String foodName,String foodImageUrl,int price)
    {
        this.vendor = vendor;
        this.category = category;
        this.foodImageUrl = foodImageUrl;
        this.foodName = foodName;
        this.price = price;
    }

}
