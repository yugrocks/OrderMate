package com.tdevelopers.nasta.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.tdevelopers.nasta.R;

import java.util.ArrayList;

/**
 * Created by User on 6/4/2017.
 */

public class GridImageAdapter extends BaseAdapter
{
    private static final String tag = GridImageAdapter.class.getSimpleName();
    private Context mContext;
    private LayoutInflater mInflater;
    private int layoutResource;
    private ArrayList<String> vendors;
    private String idurl;
    private boolean wait = false, nomoreposts = false, Private = false, internetworking = true;


    public GridImageAdapter(Context context, int layoutResource, ArrayList<String> vendors)
    {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        this.layoutResource = layoutResource;
        this.vendors = vendors;
    }

    private static class ViewHolder
    {
        ImageView StallImage;
        TextView StallName;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        final ViewHolder holder;
        if (convertView == null)
        {
            convertView = mInflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder();
            holder.StallImage = (ImageView) convertView.findViewById(R.id.i1);
            holder.StallName = (TextView) convertView.findViewById(R.id.t1);
            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.StallName.setText(vendors.get(position));


/*                Glide.with(mContext)
                        .load(imgURL)
                        .listener(new RequestListener<Drawable>()
                        {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                        Target<Drawable> target, boolean isFirstResource)
                            {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model,
                                                           Target<Drawable> target, DataSource dataSource,
                                                           boolean isFirstResource)
                            {
                                if (holder.mProgressBar != null)
                                {
                                    holder.mProgressBar.setVisibility(View.GONE);
                                }
                                return false;
                            }
                        })
                        .into(holder.image);*/
        /*    convertView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(mContext, SwipePic.class);
                    intent.putStringArrayListExtra("urls", imgURLs);
                    intent.putExtra("id", idurl);
                    intent.putExtra("position", position);
                    mContext.startActivity(intent);
                }
            });*/
        return convertView;
    }

    @Override
    public int getCount()
    {
        return vendors.size();
    }

    @Override
    public Object getItem(int position)
    {
        return vendors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}



















