package com.example.denis.popularmoviesstages1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.denis.popularmoviesstages1.Adapter.MoviesAdapter;
import com.example.denis.popularmoviesstages1.data.Movie;
import com.example.denis.popularmoviesstages1.network.MoviesLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Movie>>{
    public RecyclerView recyclerView;
    public MoviesAdapter adapter;

    private static final int MOVIES_LOADER_ID = 1;
//    insert api key here
    private String top_rated_url =
            "http://api.themoviedb.org/3/movie/popular?api_key=7bdc0adfb0ccb7eadb85e9fae2e84742";
    private String popular_url =
            "http://api.themoviedb.org/3/movie/popular?api_key=7bdc0adfb0ccb7eadb85e9fae2e84742";
    private static final int grid_span_count = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),
                grid_span_count);
        recyclerView.setLayoutManager(layoutManager);

        ConnectivityManager connMgr;
        connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(MOVIES_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.progressBar);
            loadingIndicator.setVisibility(View.GONE);

        }
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        return new MoviesLoader(getApplicationContext(), popular_url);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        View loadingIndicator = findViewById(R.id.progressBar);
        loadingIndicator.setVisibility(View.GONE);
        if (data != null && !data.isEmpty()) {
            adapter = new MoviesAdapter(this, (ArrayList<Movie>) data);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {


    }
}
