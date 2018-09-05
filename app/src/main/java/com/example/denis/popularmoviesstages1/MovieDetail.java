package com.example.denis.popularmoviesstages1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denis.popularmoviesstages1.data.Movie;
import com.example.denis.popularmoviesstages1.databinding.ActivityMovieDetailBinding;
import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {

    private ImageView posterView;
    private Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        posterView = findViewById(R.id.poster_view);
        ActivityMovieDetailBinding binder = DataBindingUtil.setContentView(this,
                R.layout.activity_movie_detail);

        Intent intent = getIntent();
        if (intent != null){
            movie = new Movie(
                    intent.getStringExtra(String.valueOf(R.string.intent_title)),
                    intent.getStringExtra(String.valueOf(R.string.intent_poster)),
                    intent.getStringExtra(String.valueOf(R.string.intent_plot)),
                    intent.getStringExtra(String.valueOf(R.string.intent_rating)),
                    intent.getStringExtra(String.valueOf(R.string.intent_rating))
            );

            binder.setMovie(movie);

            Picasso.with(getApplicationContext())
                    .load(intent.getStringExtra(String.valueOf(R.string.intent_poster)))
                    .into(posterView);


        }


    }
}
