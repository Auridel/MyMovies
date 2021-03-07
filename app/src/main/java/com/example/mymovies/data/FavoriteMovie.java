package com.example.mymovies.data;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "favorite_movies")
public class FavoriteMovie extends Movie{
    public FavoriteMovie(int uniqueId, int id, int voteCount, String title, String originalTitle,
                         String overview, String posterURL, String bigPosterURL,
                         String backdropURL, double voteAverage, String releaseDate) {
        super(uniqueId, id, voteCount, title, originalTitle, overview, posterURL, bigPosterURL, backdropURL, voteAverage, releaseDate);
    }

    @Ignore
    public FavoriteMovie(Movie movie) {
        super(movie.getUniqueId(), movie.getId(), movie.getVoteCount(), movie.getTitle(), movie.getOriginalTitle(),
                movie.getOverview(), movie.getPosterURL(), movie.getBigPosterURL(),
                movie.getBackdropURL(), movie.getVoteAverage(), movie.getReleaseDate());
    }
}
