package com.example.oliofinnkinoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RatingDetails extends AppCompatActivity {

    TextView movieName, ratingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_details);

        Intent intent = getIntent();
        String text1 = intent.getStringExtra(SavedMoviesList.EXTRA_TEXT);

        movieName = findViewById(R.id.movieName);
        ratingText = findViewById(R.id.ratingText);

        movieName.setText(text1);
    }
}