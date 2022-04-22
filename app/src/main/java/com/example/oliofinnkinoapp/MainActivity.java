package com.example.oliofinnkinoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import java.util.List;

//Group: gamers
//Finnkino Movie App

public class MainActivity extends AppCompatActivity {

    private Context context;
    private List<MovieClass> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //TEST
        WebReader webReader = WebReader.getInstance();
        FileSaver fileSaver = FileSaver.getInstance();

        webReader.readXML();
        fileSaver.Write(context);
        fileSaver.readMovies(context);



    }
}