package com.example.denis.popularmoviesstages1.network;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.denis.popularmoviesstages1.data.Movie;
import com.example.denis.popularmoviesstages1.data.QueryUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 28/08/18.
 */

public class MoviesLoader extends AsyncTaskLoader<List<Movie>> {
    private String url;
    public MoviesLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        ArrayList<Movie> movies = QueryUtils.extractMovies(url);
        return movies;
    }
}
