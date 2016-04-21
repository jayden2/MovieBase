package com.jayden.moviebase;

/**
 * Created by Jayden on 21-Apr-16.
 */
public class MovieTitle {
    private String title, type, rating, year, genre, plot;
    private long movieId, runtime;

    public MovieTitle(String title, String type, String rating, String year, String plot) {

        this.title = title;
        this.type = type;
        this.rating = rating;
        this.year = year;
        this.plot = plot;
    }

    public MovieTitle(String title, String type, String rating, String genre, String plot,
                      long movieId, String year, long runtime) {

        this.title = title;
        this.type = type;
        this.rating = rating;
        this.genre = genre;
        this.plot = plot;
        this.movieId = movieId;
        this.year = year;
        this.runtime = runtime;
    }

    //getters
    public String getTitle() { return title; }

    public String getType() { return type; }

    public String getRating() { return rating; }

    public String getGenre() { return genre; }

    public String getPlot() { return plot; }

    public long getMovieId() { return movieId;  }

    public String getYear() { return year; }

    public long getRuntime() { return runtime; }
}
