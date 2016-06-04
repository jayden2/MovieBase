package com.jayden.moviebase;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivityListFragment extends ListFragment implements MoviesGetHolder {

    private static MovieAdapter movieAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //get all movies from API
        JSONTasker tasker = new JSONTasker(this);
        tasker.execute("https://moviebaseapi.herokuapp.com/api/movies/?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InVzZXIxMDBAZW1haWwuY29tIiwicGFzc3dvcmQiOiJhYmMxMjMiLCJpYXQiOjE0NjQ4ODc2MjcsImV4cCI6MTQ2NTQ5MjQyN30.rmRPkQaM4iN73xQbaaCM-33wH4-6PnrQZfH5UH3oa0g");

        //set divider between fragments color and height
        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.darker_gray));
        getListView().setDividerHeight(1);

        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void getMoviesFinished(ArrayList<MovieTitle> movieList) {
        movieAdapter = new MovieAdapter(getActivity(), movieList);
        setListAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}