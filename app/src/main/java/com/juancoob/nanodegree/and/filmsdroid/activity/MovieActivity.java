package com.juancoob.nanodegree.and.filmsdroid.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.juancoob.nanodegree.and.filmsdroid.MovieDetail.MovieDetailFragment;
import com.juancoob.nanodegree.and.filmsdroid.MovieList.IMovieListContract;
import com.juancoob.nanodegree.and.filmsdroid.MovieList.MovieListFragment;
import com.juancoob.nanodegree.and.filmsdroid.MovieList.MovieListPresenter;
import com.juancoob.nanodegree.and.filmsdroid.R;
import com.juancoob.nanodegree.and.filmsdroid.model.Movie;
import com.juancoob.nanodegree.and.filmsdroid.util.ActivityUtils;
import com.juancoob.nanodegree.and.filmsdroid.util.Constants;

public class MovieActivity extends AppCompatActivity implements IMovieListContract {

    private MovieListFragment mMovieListFragment;
    private MovieListPresenter mMovieListPresenter;
    private MovieDetailFragment mMovieDetailFragment;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        if(getSupportFragmentManager().findFragmentById(R.id.contentFrame) == null
                || getSupportFragmentManager().findFragmentById(R.id.contentFrame) instanceof MovieListFragment) {
            mMovieListFragment = (MovieListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
            if (mMovieListFragment == null) {
                // Create the fragment
                mMovieListFragment = MovieListFragment.getInstance();
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mMovieListFragment, R.id.contentFrame);
            }

            // Create the presenter
            mMovieListPresenter = new MovieListPresenter(mMovieListFragment);
            mMovieListFragment.setPresenter(mMovieListPresenter);

            // If it loaded movies, it'll retrieve them again, otherwise it'll load them
            if (savedInstanceState != null) {
                mMovieListPresenter.setMovieList(savedInstanceState.<Movie>getParcelableArrayList(Constants.MOVIE_LIST));
                mMovieListPresenter.setOptionSelected(Constants.MOVIE_OPTION);
            }

        } else if(getSupportFragmentManager().findFragmentById(R.id.contentFrame) instanceof MovieDetailFragment) {
            mMovieDetailFragment = (MovieDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
            if(mMovieDetailFragment == null) {
                mMovieDetailFragment = MovieDetailFragment.getInstance();
                ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mMovieDetailFragment, R.id.contentFrame);
            }
            if (savedInstanceState != null) {
                mMovieDetailFragment.setMovie((Movie) savedInstanceState.getParcelable(Constants.MOVIE));
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(mMovieListFragment != null && getSupportFragmentManager().findFragmentById(R.id.contentFrame) instanceof MovieListFragment) {
            showMenu();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(mMovieListPresenter != null) {
            outState.putParcelableArrayList(Constants.MOVIE_LIST, mMovieListPresenter.getMovieList());
            outState.putString(Constants.MOVIE_OPTION, mMovieListPresenter.getOptionSelected());
        }
        if(mMovieDetailFragment != null) {
            outState.putParcelable(Constants.MOVIE, mMovieDetailFragment.getMovie());
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular_movies:
                mMovieListPresenter.setOptionSelected("popular");
                mMovieListFragment.showProgressBar();
                mMovieListPresenter.fetchMovies();
                // Show a new disabled menu item to let us know which option was selected
                mMenu.findItem(R.id.popular_movies_label).setVisible(true);
                mMenu.findItem(R.id.top_rated_label).setVisible(false);
                break;
            case R.id.top_rated:
                mMovieListPresenter.setOptionSelected("top");
                mMovieListFragment.showProgressBar();
                mMovieListPresenter.fetchMovies();
                mMenu.findItem(R.id.top_rated_label).setVisible(true);
                mMenu.findItem(R.id.popular_movies_label).setVisible(false);
                break;
            case android.R.id.home:
                onBackPressed();
                showMenu();
                upFunctionalityEnabled(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnFragmentInteraction(String id, @Nullable Movie movie) {
        switch (id) {
            case Constants.MOVIE_DETAIL:
                mMovieDetailFragment = MovieDetailFragment.getInstance();
                mMovieDetailFragment.setMovie(movie);
                ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mMovieDetailFragment, R.id.contentFrame);
                hideMenu();
                upFunctionalityEnabled(true);
                break;
        }
    }

    public void hideMenu() {
        mMenu.findItem(R.id.top_rated).setVisible(false);
        mMenu.findItem(R.id.popular_movies).setVisible(false);
        mMenu.findItem(R.id.top_rated_label).setVisible(false);
        mMenu.findItem(R.id.popular_movies_label).setVisible(false);
    }

    public void showMenu() {
        if(mMovieListPresenter.getOptionSelected().equals("popular")) {
            mMenu.findItem(R.id.top_rated).setVisible(true);
            mMenu.findItem(R.id.popular_movies).setVisible(true);
            mMenu.findItem(R.id.top_rated_label).setVisible(false);
            mMenu.findItem(R.id.popular_movies_label).setVisible(true);
        } else {
            mMenu.findItem(R.id.top_rated).setVisible(true);
            mMenu.findItem(R.id.popular_movies).setVisible(true);
            mMenu.findItem(R.id.top_rated_label).setVisible(true);
            mMenu.findItem(R.id.popular_movies_label).setVisible(false);
        }
    }

    public void upFunctionalityEnabled(boolean isEnabled) {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isEnabled);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        upFunctionalityEnabled(false);
    }

    public MovieListFragment getMovieListFragment() {
        return mMovieListFragment;
    }

    public void setMovieListFragment(MovieListFragment mMovieListFragment) {
        this.mMovieListFragment = mMovieListFragment;
    }

    public MovieListPresenter getMovieListPresenter() {
        return mMovieListPresenter;
    }

    public void setMovieListPresenter(MovieListPresenter mMovieListPresenter) {
        this.mMovieListPresenter = mMovieListPresenter;
    }

    public MovieDetailFragment getMovieDetailFragment() {
        return mMovieDetailFragment;
    }

    public void setMovieDetailFragment(MovieDetailFragment mMovieDetailFragment) {
        this.mMovieDetailFragment = mMovieDetailFragment;
    }
}
