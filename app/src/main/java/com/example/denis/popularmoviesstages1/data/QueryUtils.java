package com.example.denis.popularmoviesstages1.data;

import android.util.Log;

import com.example.denis.popularmoviesstages1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by denis on 28/08/18.
 */

public class QueryUtils {
    private QueryUtils(){

    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);
        }catch (MalformedURLException e){
            Log.e(String.valueOf(R.string.tag), String.valueOf(R.string.url_error), e);
        }
        return url;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                    Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while(line!=null){
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(String.valueOf(R.string.tag),String.valueOf(R.string.tag)+
                        urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(String.valueOf(R.string.tag), String.valueOf(R.string.json_error), e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    public static ArrayList<Movie> extractMovies(String moviesUrl){
        ArrayList<Movie> moviesList = new ArrayList<>();

        URL url = createUrl(moviesUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(String.valueOf(R.string.tag), String.valueOf(R.string.inputstream_error), e);
        }

        try{
            JSONObject responseObject = new JSONObject(jsonResponse);
            JSONArray moviesArray = new JSONArray(responseObject.
                    getJSONArray(String.valueOf(R.string.response_array)));

            for (int i=0; i < moviesArray.length(); i++){
                JSONObject currentMovie = moviesArray.getJSONObject(i);

                String title = currentMovie.getString("title");
                String poster_path = currentMovie.getString("poster_path");
                String synopsis = currentMovie.getString("overview");
                String user_rating = currentMovie.getString("voter_average");
                String release_date = currentMovie.getString("release_date");

                String poster_url = String.valueOf(R.string.base_poster_url)+poster_path;

                Movie movie = new Movie(title, poster_url, synopsis, user_rating, release_date);
                moviesList.add(movie);
            }

        }catch (JSONException e){
            Log.d(String.valueOf(R.string.tag),e.toString());
        }
        return moviesList;
    }

    public ArrayList<String> extractPosters(ArrayList<Movie> movies){
        ArrayList<String> posterUrls = new ArrayList<>();
        if (movies != null && !movies.isEmpty()){
            String url_poster = null;
            for (Movie movie : movies){
                url_poster = movie.getPoster_url();
                posterUrls.add(url_poster);
            }
        }
        return posterUrls;
    }

}
