package com.juancoob.nanodegree.and.filmsdroid.adapter;

import com.juancoob.nanodegree.and.filmsdroid.model.Movie;

import java.util.ArrayList;

/**
 * Created by Juan Antonio Cobos Obrero on 25/02/18.
 */

public interface IMovieListAdapterContract {
    void updateMovieList(ArrayList<Movie> movieList);
}
