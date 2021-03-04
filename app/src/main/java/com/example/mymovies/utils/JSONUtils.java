package com.example.mymovies.utils;

import android.net.Uri;

import com.example.mymovies.data.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JSONUtils {
    private static final String KEY_RESULTS = "results";
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
        if(jsonObject == null) return result;
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
                Movie movie = new Movie(id, voteCount,title, originalTitle, overview, posterURL, bigPosterURL, backdropURL, voteAverage, releaseDate);
                result.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
