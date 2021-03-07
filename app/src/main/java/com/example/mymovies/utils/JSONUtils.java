package com.example.mymovies.utils;

import com.example.mymovies.data.Movie;
import com.example.mymovies.data.Review;
import com.example.mymovies.data.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtils {
    private static final String KEY_RESULTS = "results";

    private static final String KEY_AUTHOR = "author";
    private static final String KEY_CONTENT = "content";

    private static final String KEY_VIDEO_KEY = "key";
    private static final String KEY_VIDEO_NAME = "name";
    private static final String BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v=";

    private static final String KEY_VOTE_COUNT = "vote_count";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ORIGINAL_TITLE = "original_title";
    private static final String KEY_OVERVIEW = "overview";
    private static final String KEY_POSTER_PATH = "poster_path";
    private static final String KEY_BACKDROP_PATH = "backdrop_path";
    private static final String KEY_VOTE_AVERAGE = "vote_average";
    private static final String KEY_RELEASE_DATE = "release_date";

    private static final String BASE_IMAGES_URL = "https://image.tmdb.org/t/p/";
    private static final String SMALL_POSTER_SIZE = "w185";
    private static final String BIG_POSTER_SIZE = "w780";

    public static ArrayList<Movie> getMoviesFromJSON(JSONObject jsonObject) {
        ArrayList<Movie> result = new ArrayList<>();
        if (jsonObject == null) return result;
        try {
            JSONArray jsonArray = jsonObject.optJSONArray(KEY_RESULTS);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objMovie = jsonArray.getJSONObject(i);
                int id = objMovie.getInt(KEY_ID);
                int voteCount = objMovie.getInt(KEY_VOTE_COUNT);
                String title = objMovie.getString(KEY_TITLE);
                String originalTitle = objMovie.getString(KEY_ORIGINAL_TITLE);
                String overview = objMovie.getString(KEY_OVERVIEW);
                String posterURL = BASE_IMAGES_URL + SMALL_POSTER_SIZE + objMovie.getString(KEY_POSTER_PATH);
                String bigPosterURL = BASE_IMAGES_URL + BIG_POSTER_SIZE + objMovie.getString(KEY_POSTER_PATH);
                String backdropURL = objMovie.getString(KEY_BACKDROP_PATH);
                double voteAverage = objMovie.getDouble(KEY_VOTE_AVERAGE);
                String releaseDate = objMovie.getString(KEY_RELEASE_DATE);
                Movie movie = new Movie(id, voteCount, title, originalTitle, overview, posterURL, bigPosterURL, backdropURL, voteAverage, releaseDate);
                result.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Review> getReviewsFromJSON(JSONObject jsonObject) {
        ArrayList<Review> result = new ArrayList<>();
        if (jsonObject == null) return result;
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_RESULTS);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objReview = jsonArray.getJSONObject(i);
                String author = objReview.getString(KEY_AUTHOR);
                String content = objReview.getString(KEY_CONTENT);
                Review review = new Review(author, content);
                result.add(review);
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Trailer> getTrailerFromJSON(JSONObject jsonObject) {
        ArrayList<Trailer> result = new ArrayList<>();
        if (jsonObject == null) return result;
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_RESULTS);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objTrailer = jsonArray.getJSONObject(i);
                String key = BASE_YOUTUBE_URL + objTrailer.getString(KEY_VIDEO_KEY);
                String name = objTrailer.getString(KEY_VIDEO_NAME);
                Trailer trailer = new Trailer(key, name);
                result.add(trailer);
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
