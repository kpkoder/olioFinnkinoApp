package com.example.oliofinnkinoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SavedMoviesList extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.oliofinnkinoapp.EXTRA_TEXT";

    ListView listView;
    String[] AllMovies = {"test1", "test2", "test3"};

    List<String> listOfMovies = Arrays.asList(AllMovies);
    List<String> SavedList = new ArrayList<String>();

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_movies_list);

        FileSaver fileSaver = FileSaver.getInstance();
        fileSaver.searchReviews(SavedMoviesList.this);

        this.SavedList = fileSaver.returnMovieSaved();
        //REMOVING EXTRA CHARS
        List<String> tempList = new ArrayList<>();
        tempList.clear();
        for (String i : SavedList) {
            String[] s = i.split("-");
            String s1 = s[1];
            tempList.add(s1);
        }

        String[] adapMovieList = tempList.stream().toArray(String[]::new);

        listView = findViewById(R.id.listview2);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, adapMovieList);
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