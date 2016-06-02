package com.jayden.moviebase;

/**
 * Created by Jayden on 21-Apr-16.
 */
public class MovieTitle {
    private String title, type, rating, year, genre, plot, review;
    private long movieId, runtime, score;

    public MovieTitle(String title, String type, String rating, String year, String review, long score) {

        this.title = title;
        this.type = type;
        this.rating = rating;
        this.year = year;
        this.review = review;
        this.score = score;
    }

    public MovieTitle(String title, String type, String rating, String genre, String plot,
                      long movieId, String year, long runtime, String review, long score) {

        this.title = title;
        this.type = type;
        this.rating = rating;
        this.genre = genre;
        this.plot = plot;
        this.movieId = movieId;
        this.year = year;
        this.runtime = runtime;
        this.score = score;
        this.review = review;
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

    public long getScore() { return score; }

    public String getReview() { return review; }
}
