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

    TextView rateCount, showRating, lengthBar;
    EditText review, yourNameText;
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
        lengthBar = findViewById(R.id.movieLength);
        yourNameText = findViewById(R.id.yourNameText);

        //fetching length in minutes
        FileSaver fileSaver = FileSaver.getInstance();
        String text2 = fileSaver.getMovieLength(text1)+"\nmin";
        lengthBar.setText(text2);

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
                String yourName = yourNameText.getText().toString();
                fileSaver.writeReview(yourName,rateValue,tempMName, review.getText().toString(),RateMovieActivity.this);
                //reset
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");
            }
        });
    }

}