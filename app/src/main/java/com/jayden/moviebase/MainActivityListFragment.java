package com.jayden.moviebase;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivityListFragment extends ListFragment {

    private ArrayList<MovieTitle> movies;

    private MovieAdapter movieAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //get all movies from API
        JSONTasker tasker = new JSONTasker();
        tasker.execute("https://moviebaseapi.herokuapp.com/api/movies/?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InVzZXIxMDBAZW1haWwuY29tIiwicGFzc3dvcmQiOiJhYmMxMjMiLCJpYXQiOjE0NjQ4ODc2MjcsImV4cCI6MTQ2NTQ5MjQyN30.rmRPkQaM4iN73xQbaaCM-33wH4-6PnrQZfH5UH3oa0g");

        movies = new ArrayList<MovieTitle>();
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah", 6));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah", 6));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah", 6));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah", 6));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah", 6));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah", 6));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah", 6));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah", 6));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah", 6));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah", 6));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah", 6));
        movies.add(new MovieTitle("Bad Boys", "movie", "MA", "Action", "blah blah blah", 6));


        movieAdapter = new MovieAdapter(getActivity(), movies);

        setListAdapter(movieAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}