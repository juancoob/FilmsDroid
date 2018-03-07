package com.juancoob.nanodegree.and.filmsdroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.juancoob.nanodegree.and.filmsdroid.MovieDetail.MovieDetailFragment;
import com.juancoob.nanodegree.and.filmsdroid.R;
import com.juancoob.nanodegree.and.filmsdroid.model.Movie;
import com.juancoob.nanodegree.and.filmsdroid.util.ActivityUtils;
import com.juancoob.nanodegree.and.filmsdroid.util.Constants;

/**
 * Created by Juan Antonio Cobos Obrero on 07/03/2018.
 */

public class MovieDetailActivity extends AppCompatActivity {

    private MovieDetailFragment mMovieDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();

        // Add the up functionality
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mMovieDetailFragment = (MovieDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mMovieDetailFragment == null) {
            // Create the movie detail fragment
            mMovieDetailFragment = MovieDetailFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mMovieDetailFragment, R.id.contentFrame);
        }
        if(intent.hasExtra(Constants.MOVIE_DETAIL)) {
            mMovieDetailFragment.setMovie((Movie) intent.getParcelableExtra(Constants.MOVIE_DETAIL));
        }
        if (savedInstanceState != null) {
            mMovieDetailFragment.setMovie((Movie) savedInstanceState.getParcelable(Constants.MOVIE));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Constants.MOVIE, mMovieDetailFragment.getMovie());
        super.onSaveInstanceState(outState);
    }
}

