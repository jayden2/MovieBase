package com.jayden.moviebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Jayden on 06-Jun-16.
 */
public class SearchAddListFragment extends ListFragment implements SearchSetHolder {

    private static SearchAddAdapter searchAddAdapter;
    private ArrayList<MovieTitle> searchArray;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ////////////////////////

        MovieTitle movie = new MovieTitle();

        movie.setTitle("Title");
        movie.setYear(1993);

        //adding object to array list
        searchArray.add(movie);

        searchAddAdapter = new SearchAddAdapter(getActivity(), searchArray);
        setListAdapter(searchAddAdapter);

        ////////////////////

        //set divider between fragments color and height
        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.darker_gray));
        getListView().setDividerHeight(1);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //launch create intent store IMDB id for next view
    }

    public void newSearchResults(String search) {
        //perform search query
        OMDBTasker tasker = new OMDBTasker(this);
        tasker.execute("http://www.omdbapi.com/?s=" + search + "&type=movie");
    }
    //once the async JSON movie list get task is done, create and set the Movie adapter
    @Override
    public void getSearchFinished(ArrayList<MovieTitle> searchList) {
        Log.d("Search Finished!!!", "");
        Log.d("", String.valueOf(searchList));
        searchAddAdapter = new SearchAddAdapter(getActivity().getApplicationContext(), searchList);
        //setListAdapter(searchAddAdapter);
        //searchAddAdapter.notifyDataSetChanged();
    }

    //TODO LAUNCH DETAIL ADD REVIEW
}
