package com.example.oliofinnkinoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//Group: gamers
//Finnkino Movie App


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TEST
        WebReader webReader = WebReader.getInstance();
        webReader.readXML();

    }
}