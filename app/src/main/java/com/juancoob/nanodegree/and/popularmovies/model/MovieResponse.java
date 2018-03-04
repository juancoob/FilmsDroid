package com.juancoob.nanodegree.and.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Juan Antonio Cobos Obrero on 22/02/18.
 */

public class MovieResponse {

    @SerializedName("page")
    private int mPage;

    @SerializedName("total_results")
    private int mTotalResults;

    @SerializedName("total_pages")
    private int mTotalPages;

    @SerializedName("results")
    private ArrayList<Movie> mMovieList;

    public int getPage() {
        return mPage;
    }

    public void setPage(int mPage) {
        this.mPage = mPage;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(int mTotalResults) {
        this.mTotalResults = mTotalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(int mTotalPages) {
        this.mTotalPages = mTotalPages;
    }

    public ArrayList<Movie> getMovieList() {
        return mMovieList;
    }

    public void setMovieList(ArrayList<Movie> mMovieList) {
        this.mMovieList = mMovieList;
    }

}
