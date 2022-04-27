package com.example.oliofinnkinoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import java.util.List;

//Group: gamers
//Finnkino Movie App

public class MainActivity extends AppCompatActivity {

    private Context context;
    private List<MovieClass> movieList;
    Button searchButton, myMoviesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Instances
        WebReader webReader = WebReader.getInstance();
        FileSaver fileSaver = FileSaver.getInstance();

        //MUST BE RAN AT START
        webReader.readXML();            //reads movies from https://www.finnkino.fi/xml
        fileSaver.Write(context);      //makes a file of the movies on your device
        fileSaver.readAndWriteMovies(context); //for reading and re-writing said file ^
        //END

        //TEST AREA
        //

        searchButton = findViewById(R.id.searchButton);
        myMoviesButton = findViewById(R.id.myMoviesButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchMovies();
            }
        });

        myMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSavedMovies();
            }
        });

    }


    public void searchMovies() {

        Intent intent = new Intent(this, SearchMovieActivity.class);
        startActivity(intent);
    }

    public void showSavedMovies() {

        Intent intent = new Intent(this, SavedMoviesList.class);
        startActivity(intent);
    }

}