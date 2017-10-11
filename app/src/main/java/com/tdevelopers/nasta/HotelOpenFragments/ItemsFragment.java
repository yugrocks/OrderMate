package com.tdevelopers.nasta.HotelOpenFragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdevelopers.nasta.Adapters.HotelItemsAdapter;
import com.tdevelopers.nasta.Entities.Dish;
import com.tdevelopers.nasta.Entities.JSONdata;
import com.tdevelopers.nasta.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ItemsFragment extends Fragment {

    RecyclerView breakFast, Lunch, Dinner;
    private ArrayList<JSONdata> jsoNdatas;
    private String vendor;
    private String username;

    @SuppressLint("ValidFragment")
    public ItemsFragment() {
        // Required empty public constructor

    }

    public static ItemsFragment newInstance(ArrayList<JSONdata> jsoNdatas, String username,String vendor) {

        Bundle args = new Bundle();
        args.putParcelableArrayList("data", jsoNdatas);
        args.putString("vendor", vendor);
        args.putString("username",username);

        ItemsFragment fragment = new ItemsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        breakFast = (RecyclerView) view.findViewById(R.id.breaksrv);
        Lunch = (RecyclerView) view.findViewById(R.id.fastfoodrv);
        Dinner = (RecyclerView) view.findViewById(R.id.mealsrv);

        breakFast.setNestedScrollingEnabled(false);
        Lunch.setNestedScrollingEnabled(false);
        Dinner.setNestedScrollingEnabled(false);

        jsoNdatas = getArguments().getParcelableArrayList("data");
        vendor = getArguments().getString("vendor");
        username = getArguments().getString("username");
        Log.d("Item Fragment", vendor + String.valueOf(jsoNdatas.size()));

        breakFast.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        Lunch.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        Dinner.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Dish> dishes = new ArrayList<>();
        for (JSONdata temp : jsoNdatas) {
            if ((temp.vendor.equals(vendor)) && (temp.foodType.equals("bf")))
                dishes.add(new Dish(temp.foodImageUrl, temp.foodName,temp.price));
        }
        Log.d("Item Fragment", "bf" + String.valueOf(dishes.size()));
        breakFast.setAdapter(new HotelItemsAdapter(dishes,username,vendor));

        dishes = new ArrayList<>();
        for (JSONdata temp : jsoNdatas) {
            if ((temp.vendor.equals(vendor)) && (temp.foodType.equals("lun")))
                dishes.add(new Dish(temp.foodImageUrl, temp.foodName,temp.price));
        }
        Log.d("Item Fragment", "lun" + String.valueOf(dishes.size()));
        Lunch.setAdapter(new HotelItemsAdapter(dishes,username,vendor));
        dishes = new ArrayList<>();
        for (JSONdata temp : jsoNdatas) {
            if ((temp.vendor.equals(vendor)) && (temp.foodType.equals("din")))
                dishes.add(new Dish(temp.foodImageUrl, temp.foodName,temp.price));
        }
        Log.d("Item Fragment", "lun" + String.valueOf(dishes.size()));
        Dinner.setAdapter(new HotelItemsAdapter(dishes,username,vendor));

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
