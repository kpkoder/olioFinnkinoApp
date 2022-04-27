package com.example.oliofinnkinoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RatingDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_details);

        Intent intent = getIntent();
        String text1 = intent.getStringExtra(SavedMoviesList.EXTRA_TEXT);

        TextView textView = findViewById(R.id.movieName);
        textView.setText(text1);
    }
}