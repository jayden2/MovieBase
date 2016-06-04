package com.jayden.moviebase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Jayden on 21-Apr-16.
 */
public class MovieAdapter extends ArrayAdapter<MovieTitle> {

    public static class ViewHolder {
        TextView movieTitle;
        TextView movieRating;
        TextView movieYear;
        TextView movieReview;
        TextView movieScore;
    }

    public MovieAdapter(Context context, List<MovieTitle> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("VIEW RUNNING?!","VIEW RUNNING?!");
        //get the data item for this position
        MovieTitle movie = getItem(position);

        //create view holder
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            Log.d("IS THIS NULL????","");
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

            viewHolder.movieTitle = (TextView) convertView.findViewById(R.id.listItemMovieTitle);
            viewHolder.movieRating = (TextView) convertView.findViewById(R.id.listItemMovieRating);
            viewHolder.movieYear = (TextView) convertView.findViewById(R.id.listItemMovieYear);
            viewHolder.movieReview = (TextView) convertView.findViewById(R.id.listItemMovieReview);
            viewHolder.movieScore = (TextView) convertView.findViewById(R.id.listItemMovieReviewScore);

            convertView.setTag(viewHolder);

        } else {
            Log.d("RUN THIS!!!????","");
            //Grab widgets from view holder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //populate data into the template view
        viewHolder.movieTitle.setText(movie.getTitle());
        viewHolder.movieRating.setText(movie.getRating());
        viewHolder.movieYear.setText(Long.toString(movie.getYear()));
        viewHolder.movieReview.setText(movie.getReview());
        viewHolder.movieScore.setText(Long.toString(movie.getScore()));

        return convertView;
    }
}
