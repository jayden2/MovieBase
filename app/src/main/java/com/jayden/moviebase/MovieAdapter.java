package com.jayden.moviebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jayden on 21-Apr-16.
 */
public class MovieAdapter extends ArrayAdapter<MovieTitle> {

    public MovieAdapter(Context context, ArrayList<MovieTitle> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieTitle movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }

        TextView movieTitle = (TextView) convertView.findViewById(R.id.listItemMovieTitle);
        TextView movieRating = (TextView) convertView.findViewById(R.id.listItemMovieRating);
        TextView movieYear = (TextView) convertView.findViewById(R.id.listItemMovieYear);
        TextView movieReview = (TextView) convertView.findViewById(R.id.listItemMovieReview);
        TextView movieScore = (TextView) convertView.findViewById(R.id.listItemMovieReviewScore);

        movieTitle.setText(movie.getTitle());
        movieRating.setText(movie.getRating());
        movieYear.setText(movie.getYear());
        movieReview.setText(movie.getReview());
        movieScore.setText(Long.toString(movie.getScore()));

        return convertView;
    }
}
