package com.example.mymovies.data;

public class Movie {
    private int id;
    private int voteCount;
    private String title;
    private String originalTitle;
    private String overview;
    private String posterURL;
    private String bigPosterURL;
    private String backdropURL;
    private double voteAverage;
    private String releaseDate;

    public Movie(int id, int voteCount, String title, String originalTitle, String overview, String posterURL, String bigPosterURL, String backdropURL, double voteAverage, String releaseDate) {
        this.id = id;
        this.voteCount = voteCount;
        this.title = title;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.posterURL = posterURL;
        this.bigPosterURL = bigPosterURL;
        this.backdropURL = backdropURL;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setBigPosterURL(String bigPosterURL) {
        this.bigPosterURL = bigPosterURL;
    }

    public String getBigPosterURL() {
        return bigPosterURL;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public String getBackdropURL() {
        return backdropURL;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public void setBackdropURL(String backdropURL) {
        this.backdropURL = backdropURL;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
