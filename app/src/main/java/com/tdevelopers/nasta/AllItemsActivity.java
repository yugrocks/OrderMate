package com.tdevelopers.nasta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tdevelopers.nasta.Adapters.DishDetailedAdapter;
import com.tdevelopers.nasta.Entities.Dish;
import com.tdevelopers.nasta.Entities.JSONdata;

import java.util.ArrayList;

public class AllItemsActivity extends AppCompatActivity {

    RecyclerView allitems;
    ArrayList<JSONdata> jsoNdatas;
    String type;

    public void init() {
        allitems = (RecyclerView) findViewById(R.id.allitems);
        allitems.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Dish> data = new ArrayList<>();
        String check = "" ;
        if (type.equals("Tiffins"))
            check="BEV";
        else if (type.equals("Fast Food"))
            check="SOF";
        else if (type.equals("Veg"))
            check="VEG";
        else if (type.equals("Non-Veg"))
            check="NON";
        for (JSONdata temp: jsoNdatas)
        {
            if (temp.category.equals(check))
                data.add(new Dish(temp.foodImageUrl, temp.foodName,temp.price));
        }
        allitems.setAdapter(new DishDetailedAdapter(data));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        jsoNdatas = getIntent().getParcelableArrayListExtra("data");
        type = getIntent().getStringExtra("type");
        setSupportActionBar(toolbar);
        setTitle(type);
        init();


    }

}
