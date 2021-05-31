package com.panneer.moviecatalogservice.model;

public class RatingItem {

    private String movieId;
    private Integer rating;

    public RatingItem() {

    }

    public RatingItem(String movieId, Integer rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
