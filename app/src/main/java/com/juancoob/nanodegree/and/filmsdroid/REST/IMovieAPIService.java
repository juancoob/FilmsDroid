package com.juancoob.nanodegree.and.filmsdroid.REST;

import com.juancoob.nanodegree.and.filmsdroid.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Juan Antonio Cobos Obrero on 22/02/18.
 */

public interface IMovieAPIService {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

}
