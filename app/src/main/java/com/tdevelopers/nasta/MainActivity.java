package com.tdevelopers.nasta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.tdevelopers.nasta.Adapters.GridImageAdapter;
import com.tdevelopers.nasta.Adapters.HomeFoodItemsAdapter;
import com.tdevelopers.nasta.Entities.Dish;
import com.tdevelopers.nasta.Entities.Hotel;
import com.tdevelopers.nasta.Entities.JSONdata;
import com.tdevelopers.nasta.Opens.HotelOpenActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{

    private ArrayList<JSONdata> jsoNdata;
    private int length;
    private RecyclerView dishrv;
    private static final String tag = MainActivity.class.getSimpleName();
    private FrameLayout f[];
    private GridView gridView;
    private ImageView i[];
    private TextView t[];
    private Hotel[] data;

    public void init()
    {
        dishrv = (RecyclerView) findViewById(R.id.dishrv);
        dishrv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dishrv.setNestedScrollingEnabled(false);
        ArrayList<String> vendors = new ArrayList<>();
        ArrayList<String> vendorImageURL = new ArrayList<>();

        for (int i = 0; i < length; i++)
        {
            Log.i(tag, jsoNdata.get(i).vendor);
            if (!vendors.contains(jsoNdata.get(i).vendor))
            {
                Log.i(tag, "new : " + jsoNdata.get(i).vendor);
                vendors.add(jsoNdata.get(i).vendor);
                vendorImageURL.add(jsoNdata.get(i).vendorImageUrl);
            }
        }

        GridImageAdapter adapter = new GridImageAdapter(this, R.layout.hotelview, vendors,vendorImageURL,jsoNdata);
        gridView.setAdapter(adapter);


        /*FirebaseDatabase.getInstance().getReference("Hotels").orderByChild("rating").limitToFirst(4).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null) {
                    int ij = 0;
                    for (DataSnapshot d : dataSnapshot.getChildren()) {

                        data[ij++] = d.getValue(Hotel.class);
                    }

                    for (int k = 0; k < data.length; k++)
                        Glide.with(MainActivity.this).load(data[k].pic).into(i[k]);

                    for (int k = 0; k < data.length; k++)
                        t[k].setText(data[k].name);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Query q = FirebaseDatabase.getInstance().getReference("Dishes").orderByChild("rating").limitToFirst(10);
        dishrv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dishrv.setAdapter(new HomeFoodItemsAdapter(q, Dish.class));
*/


    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = (GridView) findViewById(R.id.gridView);
        String key = getIntent().getStringExtra("key");

        Ion.with(this).load("http://192.168.43.79:8000/data/"+key).asString().setCallback(new FutureCallback<String>()
        {
            @Override
            public void onCompleted(Exception e, String result)
            {
                Log.i(tag, result);
                try
                {
                    JSONArray jsonArray = new JSONArray(result);
                    jsoNdata = new ArrayList<>();
                    length = jsonArray.length();
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String category = jsonObject.getString("category");
                        String price = jsonObject.getString("price");
                        String vendor = jsonObject.getString("brand");
                        String name = jsonObject.getString("name");
                        String vendorImageURL = jsonObject.getString("vendor_image");
                        String image = jsonObject.getString("image");
                        JSONdata temp = new JSONdata(vendor, category, name, "http://192.168.43.79:8000" +image,"http://192.168.43.79:8000" + vendorImageURL, Integer.parseInt(price));
                        jsoNdata.add(temp);
                    }
                    init();
                } catch (JSONException je)
                {
                    Log.e(tag, "Error in JSON", je);
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MainActivity.this, CartActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
