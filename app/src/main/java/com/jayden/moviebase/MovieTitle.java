package com.jayden.moviebase;

/**
 * Created by Jayden on 21-Apr-16.
 */
public class MovieTitle {
    private String title, rating, review, cover, description, genre, updatedAt, createdAt, imdb;
    private long movieId, year, score, userId;

    public MovieTitle() {

    }

    //getters
    public String getTitle() { return title; }

    public String getRating() { return rating; }

    public long getMovieId() { return movieId;  }

    public String getDescription() { return description; }

    public String getCover() { return cover; }

    public String getUpdatedAt() { return updatedAt; }

    public String getCreatedAt() { return createdAt; }

    public long getUserId() { return userId; }

    public long getYear() { return year; }

    public String getGenre() { return genre; }

    public long getScore() { return score; }

    public String getReview() { return review; }

    public String getImdb() { return imdb; }

    //setters
    public void setTitle(String title) { this.title = title; }

    public void setRating(String rating) { this.rating = rating; }

    public void setMovieId(long movieId) { this.movieId = movieId;  }

    public void setDescription(String description) { this.description = description; }

    public void setCover(String cover) { this.cover = cover; }

    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public void setUserId(long userId) { this.userId = userId; }

    public void setYear(long year) { this.year = year; }

    public void setGenre(String genre) { this.genre = genre; }

    public void setScore(long score) { this.score = score; }

    public void setReview(String review) { this.review = review; }

    public void setImdb(String imdb) { this.imdb = imdb; }
}
