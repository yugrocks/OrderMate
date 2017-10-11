package com.tdevelopers.nasta.Adapters;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.tdevelopers.nasta.Entities.Dish;
import com.tdevelopers.nasta.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;

/**
 * Created by saitej dandge on 25-12-2016.
 */

public class HotelItemsAdapter extends RecyclerView.Adapter {
    ArrayList<Dish> data;
    Context context;
    String username;
    String vendor;
    int VIEW_TYPE_HEADER = 0;

    int VIEW_TYPE_NON_HEADER = 1;

    public HotelItemsAdapter(ArrayList<Dish> data,String username,String vendor) {
        this.data = data;
        this.username = username;
        this.vendor = vendor;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.dish_rv_item, parent, false);
            this.context = parent.getContext();
            return new HotelItemsAdapter.HotelItemsViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return VIEW_TYPE_HEADER;
        else
            return VIEW_TYPE_NON_HEADER;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder outholder,final int position) {

            HotelItemsViewHolder holder = (HotelItemsViewHolder) outholder;
            Dish current = data.get(position);
            if (current != null) {
                holder.name.setText(current.name);
                Glide.with(context).load(current.pic).into(holder.pic);
                holder.price.setText("Rs." + current.price);
            }
            holder.addtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Added to cart", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    /*JsonObject json = new JsonObject();
                    json.addProperty("foodName", data.get(position).name );
                    json.addProperty("username", username);
                    json.addProperty("count", 1);
                    Log.d("HotelItemsAdapter",username + " "+ data.get(position).name);

                    Ion.with(context)
                            .load("http://192.168.43.79:8000/receiveorder")
                            .setJsonObjectBody(json)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    Log.d("Hotel Adapter","added in cart");
                                }
                            });*/


                    postdata temp = new postdata();
                    temp.execute(position);

                }
            });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class HotelItemsViewHolder extends RecyclerView.ViewHolder {


        TextView name;
        ImageView pic;
        TextView rating;
        TextView price;
        ImageView addtocart;

        HotelItemsViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            pic = (ImageView) itemView.findViewById(R.id.pic);
            price = (TextView) itemView.findViewById(R.id.price);
            rating = (TextView) itemView.findViewById(R.id.rating);
            addtocart = (ImageView) itemView.findViewById(R.id.addtocart);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class postdata extends AsyncTask<Integer,Integer,String>
    {
        @Override
        protected String doInBackground(Integer... params) {
            String DATA=null;
            String urlParameters = "{\"username\" : \""+username+ "\",foodName : \""
                    +data.get(params[0]).name+"\", count : \"1\"}";
            Log.d("HotelItemsAdapter",urlParameters);
            String baseAddress="http://192.168.43.79:8000/receiveorder/"+urlParameters;
            try
            {
                URL url= new URL(baseAddress);
                HttpURLConnection urlconnection= (HttpURLConnection) url.openConnection();
                urlconnection.setRequestMethod("GET");
                urlconnection.connect();
            }
            catch(IOException e)
            {
                Log.e("cloudConnect", "Error ", e);
                return null;
            }

            return null;

        }
    }
}
