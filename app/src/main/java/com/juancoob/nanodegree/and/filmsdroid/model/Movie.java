package com.juancoob.nanodegree.and.filmsdroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan Antonio Cobos Obrero on 20/02/18.
 */

public class Movie implements Parcelable {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("poster_path")
    private String mImagePath;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("vote_average")
    private String mVoteAverage;

    @SerializedName("release_date")
    private String mReleaseDate;

    public Movie() {
    }

    private Movie(Parcel in) {
        mTitle = in.readString();
        mImagePath = in.readString();
        mOverview = in.readString();
        mVoteAverage = in.readString();
        mReleaseDate = in.readString();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(String mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mImagePath);
        parcel.writeString(mOverview);
        parcel.writeString(mVoteAverage);
        parcel.writeString(mReleaseDate);
    }
}
