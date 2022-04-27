package com.example.oliofinnkinoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class SavedMoviesList extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.oliofinnkinoapp.EXTRA_TEXT";

    ListView listView;
    String[] AllMovies = {"test1", "test2", "test3"};

    List<String> listOfMovies = Arrays.asList(AllMovies);

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_movies_list);

        listView = findViewById(R.id.listview2);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfMovies);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ratingInfo(i);
            }
        });
    }

    public void ratingInfo(int position) {

        Intent intent = new Intent(this, RatingDetails.class);
        String movie = listOfMovies.get(position);
        intent.putExtra(EXTRA_TEXT, movie);
        startActivity(intent);
    }
}