package com.tdevelopers.nasta;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SuperMainActivity extends AppCompatActivity {

    private ImageButton scan;
    private IntentIntegrator qrScan;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_main);

        scan = (ImageButton)findViewById(R.id.scan);

        qrScan = new IntentIntegrator(this);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {

            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

                key = result.getContents();
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("key",key);
                startActivity(intent);
                Toast.makeText(this, key, Toast.LENGTH_LONG).show();




                //if qr contains data
//                try {
//                    //converting the data to json
//                    JSONObject obj = new JSONObject(result.getContents());
//                    //setting values to textviews
//
//                    Toast.makeText(this, "Result Found", Toast.LENGTH_LONG).show();
////                    textViewName.setText(obj.getString("name"));
////                    textViewAddress.setText(obj.getString("address"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    //if control comes here
//                    //that means the encoded format not matches
//                    //in this case you can display whatever data is available on the qrcode
//                    //to a toast
//                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
//                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
