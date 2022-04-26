package com.example.oliofinnkinoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class RateMovieActivity extends AppCompatActivity {

    TextView rateCount, showRating;
    EditText review;
    Button button;
    RatingBar ratingBar;
    float rateValue; String temp;

    /*singleton principle if needed
    private static RateMovieActivity rateMovieActivity = new RateMovieActivity();
    public static RateMovieActivity getInstance() { return rateMovieActivity; }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_movie);

        Intent intent = getIntent();
        String text1 = intent.getStringExtra(SearchMovieActivity.EXTRA_TEXT);

        TextView textView = findViewById(R.id.movieName);
        textView.setText(text1);

        rateCount = findViewById(R.id.rateCount);
        ratingBar = findViewById(R.id.ratingBar);
        review = findViewById(R.id.review);
        button = findViewById(R.id.saveButton);
        showRating = findViewById(R.id.showRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                rateValue = ratingBar.getRating();

                if (rateValue == 1)
                    rateCount.setText("You give 1 star!");
                else if (rateValue == 2)
                    rateCount.setText("You give 2 stars!");
                else if (rateValue == 3)
                    rateCount.setText("You give 3 stars!");
                else if (rateValue == 4)
                    rateCount.setText("You give 4 stars!");
                else if (rateValue == 5)
                    rateCount.setText("You give 5 stars!");

            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            FileSaver fileSaver = FileSaver.getInstance();
            @Override
            public void onClick(View view) {
                temp = rateCount.getText().toString();
                showRating.setText("Your rating: \n"+temp+ "\n"+review.getText());
                String tempMName = textView.getText().toString();
                //WRITING REVIEW WHEN PRESSING SAVE
                fileSaver.writeReview(rateValue,tempMName, review.getText().toString(),RateMovieActivity.this);
                // reset everything
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");
            }
        });
    }

}