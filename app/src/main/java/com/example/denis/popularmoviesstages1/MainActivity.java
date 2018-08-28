package com.example.denis.popularmoviesstages1;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    private String top_rated_url = String.valueOf(R.string.top_rated_base_url+R.string.api_key);
    private String popular_url = String.valueOf(R.string.top_rated_base_url+R.string.api_key);
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

        adapter = new MoviesAdapter(this, new ArrayList<Movie>());
        recyclerView.setAdapter(adapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(MOVIES_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        return new MoviesLoader(getApplicationContext(), popular_url);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        if (data != null && !data.isEmpty()) {
            adapter.updateData((ArrayList<Movie>) data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {


    }
}
