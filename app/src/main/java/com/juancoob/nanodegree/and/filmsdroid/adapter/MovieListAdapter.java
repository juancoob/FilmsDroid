package com.juancoob.nanodegree.and.filmsdroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.juancoob.nanodegree.and.filmsdroid.MovieList.IMovieListContract;
import com.juancoob.nanodegree.and.filmsdroid.R;
import com.juancoob.nanodegree.and.filmsdroid.model.Movie;
import com.juancoob.nanodegree.and.filmsdroid.util.ActivityUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Juan Antonio Cobos Obrero on 22/02/18.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> implements IMovieListAdapterContract {

    private ArrayList<Movie> mMovieList = new ArrayList<>();
    private final Context mCtx;
    private final IMovieListContract mIMovieListContract;

    public MovieListAdapter(Context context, ArrayList<Movie> movieList, IMovieListContract movieListContract) {
        mCtx = context;
        mMovieList = movieList;
        mIMovieListContract = movieListContract;
    }

    @Override
    public void updateMovieList(ArrayList<Movie> updatedMovieList) {
        mMovieList.clear();
        mMovieList.addAll(updatedMovieList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(mCtx).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        // Get year from release date
        String[] releaseDate = movie.getReleaseDate().split("-");
        holder.movieTitleTextView.setText(movie.getTitle());
        holder.movieDateTextView.setText(releaseDate[0]);
        Picasso.with(mCtx).load(ActivityUtils.getImageUri(movie.getImagePath(), mCtx))
                .placeholder(R.drawable.ic_tmdb_logo)
                .into(holder.moviePosterImageView);
    }

    @Override
    public int getItemCount() {
        return mMovieList != null ? mMovieList.size() : 0;
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_movie_title)
        TextView movieTitleTextView;

        @BindView(R.id.tv_movie_date)
        TextView movieDateTextView;

        @BindView(R.id.iv_movie_poster)
        ImageView moviePosterImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mIMovieListContract.onClickListener(mMovieList.get(getAdapterPosition()));
        }
    }
}

