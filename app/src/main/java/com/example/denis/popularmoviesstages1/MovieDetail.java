package com.example.denis.popularmoviesstages1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denis.popularmoviesstages1.data.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {
    private TextView releaseView, ratingView, synopsisView, titleView;
    private ImageView posterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        titleView = findViewById(R.id.title_view);
        releaseView = findViewById(R.id.release_date_view);
        ratingView = findViewById(R.id.rating_view);
        synopsisView = findViewById(R.id.movie_synopsis_view);
        posterView = findViewById(R.id.poster_view);
        Intent intent = getIntent();
        if (intent != null){
            titleView.setText(intent.getStringExtra(String.valueOf(R.string.intent_title)));
            releaseView.setText(intent.getStringExtra(String.valueOf(R.string.intent_release)));
            ratingView.setText(intent.getStringExtra(String.valueOf(R.string.intent_rating)));
            synopsisView.setText(intent.getStringExtra(String.valueOf(R.string.intent_plot)));

            Picasso.with(getApplicationContext())
                    .load(intent.getStringExtra(String.valueOf(R.string.intent_poster)))
                    .into(posterView);
        }


    }
}
