package com.example.oliofinnkinoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;

import android.os.Bundle;
import android.os.StrictMode;

//Group: gamers
//Finnkino Movie App

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //TEST
        WebReader webReader = WebReader.getInstance();
        webReader.readXML();

    }
}