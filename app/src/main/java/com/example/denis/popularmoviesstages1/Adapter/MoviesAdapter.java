package com.example.denis.popularmoviesstages1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.denis.popularmoviesstages1.MovieDetail;
import com.example.denis.popularmoviesstages1.R;
import com.example.denis.popularmoviesstages1.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by denis on 24/08/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{
    private Context context;
    private ArrayList<Movie> movies;


    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        Picasso.with(context).setLoggingEnabled(true);
        Picasso.with(context)
                .load(movies.get(position).getPoster_url())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.poster_view);

        holder.poster_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MovieDetail.class);
                intent.putExtra(String.valueOf(R.string.intent_title), movie.getTitle());
                intent.putExtra(String.valueOf(R.string.intent_poster), movie.getPoster_url());
                intent.putExtra(String.valueOf(R.string.intent_plot), movie.getPlot_synopsis());
                intent.putExtra(String.valueOf(R.string.intent_rating), movie.getUser_rating());
                intent.putExtra(String.valueOf(R.string.intent_release), movie.getRelease_date());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder{
        ImageView poster_view;
        public MovieViewHolder(View itemView) {
            super(itemView);
            poster_view = itemView.findViewById(R.id.posterView);
        }
    }
}
