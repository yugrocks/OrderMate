package com.tdevelopers.nasta;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Checkout extends AppCompatActivity
{
    private TextView timeLeft;
    private CountDownTimer OrderReady;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        timeLeft = (TextView)findViewById(R.id.timeLeft);

        OrderReady = new CountDownTimer(900000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLeft.setText("under " + millisUntilFinished / 60000 + " minute");
            }

            public void onFinish() {
                timeLeft.setText("Your order is ready!");
            }
        }.start();


        new MyAsync().execute();


    }

    private class MyAsync extends AsyncTask<String,String,String> {
        MyAsync( ){


        }

        @Override
        protected String doInBackground(String... params) {

            //if(params.length==2)
            String u = "http://192.168.43.79:8000/data/";
            try {
                URL url=new URL(u);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                InputStream os=connection.getInputStream();


                BufferedReader reader=new BufferedReader(new InputStreamReader(os));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while((line=reader.readLine())!=null){
                    buffer.append(line);
                }

                Log.e("what the fuck",buffer.toString());



                reader.close();
                os.close();
//                InputStream is=connection.getInputStream();
//                is.close();
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String p) {
            //  super.onPostExecute(p);
            //Toast.makeText(getApplicationContext(),"name is :"+name + " email is : "+email,Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),p,Toast.LENGTH_LONG).show();
            if(p == "true"){
                OrderReady.cancel();
            }
        }
    }
}
