package com.juancoob.nanodegree.and.filmsdroid.MovieList;


import android.support.annotation.NonNull;

import com.juancoob.nanodegree.and.filmsdroid.BuildConfig;
import com.juancoob.nanodegree.and.filmsdroid.R;
import com.juancoob.nanodegree.and.filmsdroid.REST.IMovieAPIService;
import com.juancoob.nanodegree.and.filmsdroid.model.Movie;
import com.juancoob.nanodegree.and.filmsdroid.model.MovieResponse;
import com.juancoob.nanodegree.and.filmsdroid.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Juan Antonio Cobos Obrero on 18/02/18.
 */

public class MovieListPresenter implements IMovieListContract.Presenter {

    private final IMovieListContract.View mMovieListFragment;
    private ArrayList<Movie> mMovieList = new ArrayList<>();

    private String optionSelected = "popular";

    public MovieListPresenter(IMovieListContract.View movieListFragment) {
        mMovieListFragment = movieListFragment;
    }

    @Override
    public ArrayList<Movie> getMovieList() {
        return mMovieList;
    }

    public void setOptionSelected(String optionSelected) {
        this.optionSelected = optionSelected;
    }

    @Override
    public void setMovieList(ArrayList<Movie> movieList) {
        if(mMovieList != null) {
            mMovieList.clear();
        } else {
            mMovieList = new ArrayList<>();
        }

        if(movieList != null) {
            mMovieList.addAll(movieList);
        }
    }

    @Override
    public void fetchMovies() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.MOVIE_BD_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IMovieAPIService iMovieAPIService = retrofit.create(IMovieAPIService.class);
        Call<MovieResponse> responseCall = null;
        switch (optionSelected) {
            case "popular":
                responseCall = iMovieAPIService.getPopularMovies(BuildConfig.MOVIE_DB_API_KEY);
                break;
            case "top":
                responseCall = iMovieAPIService.getTopRatedMovies(BuildConfig.MOVIE_DB_API_KEY);
                break;
        }

        if(responseCall != null) {
            responseCall.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                    if(response.body() != null) {
                        mMovieList = response.body().getMovieList();
                        mMovieListFragment.showMovieList(mMovieList);
                    } else {
                        mMovieListFragment.showToast(R.string.no_api_key);
                        mMovieListFragment.hideProgressBar();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                    mMovieListFragment.hideProgressBar();
                    mMovieListFragment.showToast(R.string.call_error);
                }
            });
        } else {
            mMovieListFragment.showToast(R.string.call_error);
        }

    }

    public String getOptionSelected() {
        return optionSelected;
    }
}
