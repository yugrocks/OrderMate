package com.tdevelopers.nasta.Entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Programmer on 10-10-2017.
 */

public class JSONdata implements Parcelable
{
    public String vendor;
    public String category;
    public String foodName;
    public String foodImageUrl;
    public String vendorImageUrl;
    public int price;

    public JSONdata(String vendor,String category,String foodName,String foodImageUrl,String vendorImageUrl,int price)
    {
        this.vendor = vendor;
        this.category = category;
        this.foodImageUrl = foodImageUrl;
        this.foodName = foodName;
        this.vendorImageUrl = vendorImageUrl;
        this.price = price;
    }

    public JSONdata(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<JSONdata> CREATOR = new Parcelable.Creator<JSONdata>() {
        public JSONdata createFromParcel(Parcel in) {
            return new JSONdata(in);
        }

        public JSONdata[] newArray(int size) {

            return new JSONdata[size];
        }

    };

    public void readFromParcel(Parcel in) {
        this.vendor = in.readString();
        this.category = in.readString();
        this.foodImageUrl = in.readString();
        this.foodName = in.readString();
        this.vendorImageUrl = in.readString();
        this.price = in.readInt();

    }
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.vendor);
        dest.writeString(this.category);
        dest.writeString(this.foodImageUrl);
        dest.writeString(this.foodName);
        dest.writeString(this.vendorImageUrl);
        dest.writeInt(this.price);
    }

}
