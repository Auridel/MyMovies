package com.example.mymovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymovies.adapters.ReviewAdapter;
import com.example.mymovies.adapters.TrailerAdapter;
import com.example.mymovies.data.FavoriteMovie;
import com.example.mymovies.data.MainViewModel;
import com.example.mymovies.data.Movie;
import com.example.mymovies.data.MovieDao;
import com.example.mymovies.data.Review;
import com.example.mymovies.data.Trailer;
import com.example.mymovies.utils.JSONUtils;
import com.example.mymovies.utils.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageViewBigPoster;
    private TextView textViewTitle;
    private TextView textViewOriginalTitle;
    private TextView textViewRating;
    private TextView textViewReleaseDate;
    private TextView textViewOverview;
    private ImageView imageViewAddToFavorite;

    private RecyclerView recyclerViewTrailers;
    private RecyclerView recyclerViewReviews;
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;

    private int id;
    private Movie movie;
    private FavoriteMovie favoriteMovie;
    private static String lang;

    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        lang = Locale.getDefault().getLanguage();
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

            Picasso.get().load(movie.getBigPosterURL()).placeholder(R.drawable.placeholder780).into(imageViewBigPoster);
            textViewTitle.setText(movie.getTitle());
            textViewOriginalTitle.setText(movie.getOriginalTitle());
            textViewRating.setText(Double.toString(movie.getVoteAverage()));
            textViewReleaseDate.setText(movie.getReleaseDate());
            textViewOverview.setText(movie.getOverview());
            setFavoriteMovie();

            recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
            recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
            reviewAdapter = new ReviewAdapter();
            trailerAdapter = new TrailerAdapter();
            trailerAdapter.setOnTrailerClickListener(new TrailerAdapter.OnTrailerClickListener() {
                @Override
                public void onTrailerClick(String url) {
                    Intent intentToTrailer = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intentToTrailer);
                }
            });
            recyclerViewReviews.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewTrailers.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewTrailers.setAdapter(trailerAdapter);
            recyclerViewReviews.setAdapter(reviewAdapter);
            JSONObject jsonObjectTrailers = NetworkUtils.getJSONForVideo(movie.getId(), lang);
            JSONObject jsonObjectReviews = NetworkUtils.getJSONForReviews(movie.getId(), lang);
            ArrayList<Trailer> trailers = JSONUtils.getTrailerFromJSON(jsonObjectTrailers);
            ArrayList<Review> reviews = JSONUtils.getReviewsFromJSON(jsonObjectReviews);
            reviewAdapter.setReviews(reviews);
            trailerAdapter.setTrailers(trailers);
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itemMain: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.itemFavorite: {
                Intent intent = new Intent(this, FavoriteActivity.class);
                startActivity(intent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
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