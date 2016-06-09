package com.jayden.moviebase;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jayden on 7/06/2016.
 */
public class ReviewAddFragment extends Fragment implements SearchSetHolder {

    private Intent intent;
    public  MovieTitle movieToPost;

    public ReviewAddFragment() {
        //Empty Constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_review_add, container, false);

        intent = getActivity().getIntent();

        //get information to store to later store to database about the title
        getImdbResult(intent.getExtras().getString(MainActivity.MOVIE_IMDB_EXTRA));

        //get frament views items ready to set
        TextView title = (TextView) fragmentLayout.findViewById(R.id.movieTitle);

        //set review view display template
        title.setText(intent.getExtras().getString(MainActivity.MOVIE_TITLE_EXTRA));

        return fragmentLayout;
    }

    private void getImdbResult(String imdbID) {
        //perform search query
        OMDBTasker tasker = new OMDBTasker(this, "IMDB");
        tasker.execute("http://www.omdbapi.com/?i=" + imdbID + "&plot=short&r=json");
    }

    @Override
    public void getSearchFinished(ArrayList<MovieTitle> movieDetails) {

        //set the MovieTitle object to the the public object to then later save to the database
        if (movieDetails != null) {
            movieToPost = movieDetails.get(0);
            Log.d(movieToPost.getTitle(), "");
            Log.d(movieToPost.getDescription(), "");
            Log.d(movieToPost.getCover(), "");
        } else {
            Toast.makeText(getActivity(), "Failed to get movie details", Toast.LENGTH_SHORT).show();
        }
    }
}
