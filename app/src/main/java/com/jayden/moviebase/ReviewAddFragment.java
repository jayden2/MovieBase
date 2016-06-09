package com.jayden.moviebase;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jayden on 7/06/2016.
 */
public class ReviewAddFragment extends Fragment implements SearchSetHolder {

    private Intent intent;

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
        OMDBTasker tasker = new OMDBTasker(this);
        tasker.execute("http://www.omdbapi.com/?i=" + imdbID);
    }

    @Override
    public void getSearchFinished(ArrayList<MovieTitle> searchList) {

    }
}
