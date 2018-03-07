package com.juancoob.nanodegree.and.filmsdroid.MovieList;

import com.juancoob.nanodegree.and.filmsdroid.model.Movie;

import java.util.ArrayList;

/**
 * Created by Juan Antonio Cobos Obrero on 18/02/18.
 */

public interface IMovieListContract {

    void onClickListener(Movie movie);

    interface View {
        void showMovieList(ArrayList<Movie> mMovieList);
        void showToast(int messageId);
        void hideProgressBar();
    }

    interface Presenter {
        ArrayList<Movie> getMovieList();
        void setMovieList(ArrayList<Movie> movieList);
        void fetchMovies();
    }

}
