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
        //get the data item for this position
        MovieTitle movie = getItem(position);

        //create view holder
        ViewHolder viewHolder;

        //check if an existing view is being used otherwise inflate a new view from the custom list_row
        if (convertView == null) {

            //if there's no view being used create one, and create
            //a view holder along with it to save the view reference to it
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

            //get references of views to populate with specific note row data
            viewHolder.movieTitle = (TextView) convertView.findViewById(R.id.listItemMovieTitle);
            viewHolder.movieRating = (TextView) convertView.findViewById(R.id.listItemMovieRating);
            viewHolder.movieYear = (TextView) convertView.findViewById(R.id.listItemMovieYear);
            viewHolder.movieReview = (TextView) convertView.findViewById(R.id.listItemMovieReview);
            viewHolder.movieScore = (TextView) convertView.findViewById(R.id.listItemMovieReviewScore);

            //get tag to store the view holder that holds the reference to the widgets
            convertView.setTag(viewHolder);

        } else {

            //get widgets from view holder
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
