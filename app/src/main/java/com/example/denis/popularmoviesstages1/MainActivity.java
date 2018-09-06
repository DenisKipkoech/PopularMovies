package com.example.denis.popularmoviesstages1;

import android.app.LoaderManager;
import android.content.Loader;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.denis.popularmoviesstages1.Adapter.MoviesAdapter;
import com.example.denis.popularmoviesstages1.data.Movie;
import com.example.denis.popularmoviesstages1.network.MoviesLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Movie>>{
    public RecyclerView recyclerView;
    public MoviesAdapter adapter;


    private String selection;
    private static final int MOVIES_LOADER_ID = 1;
//    insert api key here
    private String api_key = "7bdc0adfb0ccb7eadb85e9fae2e84742";
    private String top_rated_url =
            "http://api.themoviedb.org/3/movie/top_rated?api_key="+api_key;
    private String popular_url =
            "http://api.themoviedb.org/3/movie/popular?api_key="+api_key;
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

    }

    private void getmovies(){
        LoaderManager loaderManager = getLoaderManager();
        if (loaderManager != null){
            loaderManager.destroyLoader(MOVIES_LOADER_ID);
        }
        loaderManager.initLoader(MOVIES_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        return new MoviesLoader(getApplicationContext(), selection);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {

        if (data != null && !data.isEmpty()) {
            if (adapter != null){
                adapter.updateData();
            }
            adapter = new MoviesAdapter(this, (ArrayList<Movie>) data);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_popular){
            selection = popular_url;
            getmovies();

        }
        if (id == R.id.action_top_rated){
            selection = top_rated_url;
            getmovies();

        }
        return super.onOptionsItemSelected(item);
    }
}
