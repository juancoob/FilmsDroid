package com.juancoob.nanodegree.and.filmsdroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.juancoob.nanodegree.and.filmsdroid.MovieList.IMovieListContract;
import com.juancoob.nanodegree.and.filmsdroid.MovieList.MovieListFragment;
import com.juancoob.nanodegree.and.filmsdroid.MovieList.MovieListPresenter;
import com.juancoob.nanodegree.and.filmsdroid.R;
import com.juancoob.nanodegree.and.filmsdroid.model.Movie;
import com.juancoob.nanodegree.and.filmsdroid.util.ActivityUtils;
import com.juancoob.nanodegree.and.filmsdroid.util.Constants;

public class MovieListActivity extends AppCompatActivity implements IMovieListContract {

    private MovieListFragment mMovieListFragment;
    private MovieListPresenter mMovieListPresenter;
    private Menu mMenu;
    private String mOptionSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        mMovieListFragment = (MovieListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mMovieListFragment == null) {
            // Create the movie list fragment
            mMovieListFragment = MovieListFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mMovieListFragment, R.id.contentFrame);
        }

        // Create the movie list presenter
        mMovieListPresenter = new MovieListPresenter(mMovieListFragment);
        mMovieListFragment.setPresenter(mMovieListPresenter);

        // If it loaded movies, it'll retrieve them
        if (savedInstanceState != null) {
            mMovieListPresenter.setMovieList(savedInstanceState.<Movie>getParcelableArrayList(Constants.MOVIE_LIST));
            mMovieListPresenter.setOptionSelected(savedInstanceState.getString(Constants.MOVIE_OPTION));
            mOptionSelected = savedInstanceState.getString(Constants.MOVIE_OPTION);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(Constants.MOVIE_LIST, mMovieListPresenter.getMovieList());
        outState.putString(Constants.MOVIE_OPTION, mMovieListPresenter.getOptionSelected());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        showOption(mOptionSelected);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular_movies:
                mMovieListPresenter.setOptionSelected("popular");
                mMovieListFragment.showProgressBar();
                mMovieListPresenter.fetchMovies();
                showOption(Constants.POPULAR);
                break;
            case R.id.top_rated:
                mMovieListPresenter.setOptionSelected("top");
                mMovieListFragment.showProgressBar();
                mMovieListPresenter.fetchMovies();
                showOption(Constants.TOP);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showOption(String option) {
        if(Constants.POPULAR.equals(option)) {
            mMenu.findItem(R.id.popular_movies_label).setVisible(true);
            mMenu.findItem(R.id.top_rated_label).setVisible(false);
        } else if(Constants.TOP.equals(option)) {
            mMenu.findItem(R.id.top_rated_label).setVisible(true);
            mMenu.findItem(R.id.popular_movies_label).setVisible(false);
        }
    }

    @Override
    public void onClickListener(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE_DETAIL, movie);
        startActivity(intent);
    }
}
