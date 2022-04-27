package com.example.oliofinnkinoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RatingDetails extends AppCompatActivity {

    TextView movieName, ratingText;
    Button delButton;
    String text1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_details);

        //movie's name
        Intent intent = getIntent();
        text1 = intent.getStringExtra(SavedMoviesList.EXTRA_TEXT);

        movieName = findViewById(R.id.movieName);
        ratingText = findViewById(R.id.ratingText);
        delButton = findViewById(R.id.delButton);

        //setting rating text
        FileSaver fileSaver = FileSaver.getInstance();
        String revText = fileSaver.reviewReader(text1, RatingDetails.this);
        ratingText.setText(revText);

        //parsing
        text1 = text1.substring(0, text1.length()-4);
        String[] nameRev = text1.split("_");
        String b = nameRev[1];
        movieName.setText(b);
    }

    public void setDelButton(View v) {
        FileSaver fileSaver = FileSaver.getInstance();
        String fileName = text1+".txt";
        fileSaver.fileDeleter(fileName, RatingDetails.this);
        //finish();
        // reloading
        Intent intent = new Intent(this, SavedMoviesList.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}