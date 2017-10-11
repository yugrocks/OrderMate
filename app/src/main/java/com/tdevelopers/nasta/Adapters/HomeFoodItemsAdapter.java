package com.tdevelopers.nasta.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdevelopers.nasta.AllItemsActivity;
import com.tdevelopers.nasta.Entities.JSONdata;
import com.tdevelopers.nasta.R;

import java.util.ArrayList;

/**
 * Created by saitej dandge on 20-02-2017.
 */

public class HomeFoodItemsAdapter extends RecyclerView.Adapter {

    ArrayList<String> foodType;
    ArrayList<Bitmap> foodTypeImage;
    ArrayList<JSONdata> jsoNdatas;
    Context mcontext;

    public HomeFoodItemsAdapter(Context mcontext, ArrayList<JSONdata> jsoNdatas) {
        foodType = new ArrayList<>();
        foodTypeImage = new ArrayList<>();
        foodType.add("Tiffins");
        foodType.add("Fast Food");
        foodType.add("Veg");
        foodType.add("Non-Veg");
        this.mcontext = mcontext;
        this.jsoNdatas = jsoNdatas;
        Bitmap temp = BitmapFactory.decodeResource(mcontext.getResources(), R.drawable.tiffin);
        foodTypeImage.add(temp);
        temp = BitmapFactory.decodeResource(mcontext.getResources(), R.drawable.fast);
        foodTypeImage.add(temp);
        temp = BitmapFactory.decodeResource(mcontext.getResources(), R.drawable.vegi);
        foodTypeImage.add(temp);
        temp = BitmapFactory.decodeResource(mcontext.getResources(), R.drawable.nonveg);
        foodTypeImage.add(temp);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.dish_tile, parent, false);
        return new HomeFoodItemsAdapter.HotelItemsViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder outholder,final int position) {

        HomeFoodItemsAdapter.HotelItemsViewHolder holder = (HomeFoodItemsAdapter.HotelItemsViewHolder) outholder;
        holder.name.setText(foodType.get(position));
        holder.pic.setImageBitmap(foodTypeImage.get(position));
        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, AllItemsActivity.class);
                intent.putParcelableArrayListExtra("data",jsoNdatas);
                Log.d("Gome ood Adapter",String.valueOf(jsoNdatas.size()));
                intent.putExtra("type",foodType.get(position));
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class HotelItemsViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView pic;

        HotelItemsViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            pic = (ImageView) itemView.findViewById(R.id.pic);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
