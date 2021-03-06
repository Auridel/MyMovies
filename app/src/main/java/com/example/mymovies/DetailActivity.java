package com.example.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymovies.data.FavoriteMovie;
import com.example.mymovies.data.MainViewModel;
import com.example.mymovies.data.Movie;
import com.example.mymovies.data.MovieDao;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageViewBigPoster;
    private TextView textViewTitle;
    private TextView textViewOriginalTitle;
    private TextView textViewRating;
    private TextView textViewReleaseDate;
    private TextView textViewOverview;
    private ImageView imageViewAddToFavorite;

    private int id;
    private Movie movie;
    private FavoriteMovie favoriteMovie;

    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        imageViewBigPoster = findViewById(R.id.imageViewBigPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewOriginalTitle = findViewById(R.id.textViewOriginalTitle);
        textViewRating = findViewById(R.id.textViewRating);
        textViewReleaseDate = findViewById(R.id.textViewReleaseDate);
        textViewOverview = findViewById(R.id.textViewOverview);
        imageViewAddToFavorite = findViewById(R.id.imageViewAddToFavorite);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id")) {
            id = intent.getIntExtra("id", -1);
            movie = viewModel.getMovieById(id);
            setFavoriteMovie();
            Picasso.get().load(movie.getBigPosterURL()).into(imageViewBigPoster);
            textViewTitle.setText(movie.getTitle());
            textViewOriginalTitle.setText(movie.getOriginalTitle());
            textViewRating.setText(Double.toString(movie.getVoteAverage()));
            textViewReleaseDate.setText(movie.getReleaseDate());
            textViewOverview.setText(movie.getOverview());
        } else {
            finish();
        }
    }

    public void onClickChangeFavorite(View view) {
        if(favoriteMovie == null) {
            viewModel.insertFavoriteMovie(new FavoriteMovie(movie));
            favoriteMovie = new FavoriteMovie(movie);
            imageViewAddToFavorite.setImageResource(R.drawable.favourite_remove);
            Toast.makeText(this, getResources().getText(R.string.added_to_favorites), Toast.LENGTH_SHORT).show();
        } else {
            viewModel.deleteFavoriteMovie(new FavoriteMovie(movie));
            favoriteMovie = null;
            imageViewAddToFavorite.setImageResource(R.drawable.favourite_add_to);
            Toast.makeText(this, getResources().getText(R.string.removed_from_favorites), Toast.LENGTH_SHORT).show();
        }
    }

    private void setFavoriteMovie() {
        favoriteMovie = viewModel.getFavoriteMovieById(id);
        if(favoriteMovie != null) {
            imageViewAddToFavorite.setImageResource(R.drawable.favourite_remove);
        } else  {
            imageViewAddToFavorite.setImageResource(R.drawable.favourite_add_to);
        }
    }
}