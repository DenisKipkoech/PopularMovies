package com.example.denis.popularmoviesstages1.data;

/**
 * Created by denis on 28/08/18.
 */

public class Movie {
    private String title;
    private String poster_url;
    private String plot_synopsis;
    private String user_rating;
    private String release_date;

    public Movie(String title, String poster_url, String plot_synopsis, String user_rating,
                 String release_date) {
        this.title = title;
        this.poster_url = poster_url;
        this.plot_synopsis = plot_synopsis;
        this.user_rating = user_rating;
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getPlot_synopsis() {
        return plot_synopsis;
    }

    public void setPlot_synopsis(String plot_synopsis) {
        this.plot_synopsis = plot_synopsis;
    }

    public String getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(String user_rating) {
        this.user_rating = user_rating;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
