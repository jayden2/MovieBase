package com.jayden.moviebase;

/**
 * Created by Jayden on 21-Apr-16.
 */
public class MovieTitle {
    private String title, rating, genre, review, cover, description, updatedAt, createdAt;
    private long movieId, year, score, userId;

    public MovieTitle(String title, String rating, long year, String review, long score) {

        this.title = title;
        this.rating = rating;
        this.year = year;
        this.review = review;
        this.score = score;
    }

    public MovieTitle(long movieId, String title, String rating, long score, String description, String genre,
                      String review, String cover, long year, long userId, String updatedAt, String createdAt) {

        this.title = title;
        this.rating = rating;
        this.genre = genre;
        this.description = description;
        this.movieId = movieId;
        this.year = year;
        this.cover = cover;
        this.userId = userId;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.score = score;
        this.review = review;
    }

    //getters
    public String getTitle() { return title; }

    public String getRating() { return rating; }

    public String getGenre() { return genre; }

    public long getMovieId() { return movieId;  }

    public String getDescription() { return description; }

    public String getCover() { return cover; }

    public String getUpdatedAt() { return updatedAt; }

    public String getCreatedAt() { return createdAt; }

    public long getUserId() { return userId; }

    public Long getYear() { return year; }

    public long getScore() { return score; }

    public String getReview() { return review; }
}
