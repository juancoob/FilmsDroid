package com.juancoob.nanodegree.and.popularmovies.MovieList;

import com.juancoob.nanodegree.and.popularmovies.model.Movie;

import java.util.ArrayList;

/**
 * Created by Juan Antonio Cobos Obrero on 18/02/18.
 */

public interface IMovieListContract {

    void OnFragmentInteraction(String id, Movie movie);

    interface View {
        void showMovieList(ArrayList<Movie> mMovieList);
        void showToast(int messageId);
        void hideProgressBar();
        void onClickListener(Movie movie);
    }

    interface Presenter {
        ArrayList<Movie> getMovieList();
        void setMovieList(ArrayList<Movie> movieList);
        void fetchMovies();
    }

}
