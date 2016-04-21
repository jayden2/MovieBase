package com.jayden.moviebase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivityListFragment extends ListFragment {

    private ArrayList<MovieTitle> movies;
    private MovieAdapter movieAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        movies = new ArrayList<MovieTitle>();
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah"));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah"));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah"));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah"));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah"));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah"));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah"));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah"));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah"));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah"));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah"));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah"));


        movieAdapter = new MovieAdapter(getActivity(), movies);

        setListAdapter(movieAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
