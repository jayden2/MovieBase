package com.jayden.moviebase;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Jayden on 06-Jun-16.
 */
public class SearchAddListFragment extends ListFragment implements SearchSetHolder {

    private static MovieAdapter resultAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //set divider between fragments color and height
        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.darker_gray));
        getListView().setDividerHeight(1);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //launch create intent store IMDB id for next view
    }

    @Override
    public void getSearchFinished(ArrayList<MovieTitle> searchList) {
        SearchAddAdapter searchAddAdapter = new SearchAddAdapter(getActivity(), searchList);
        setListAdapter(searchAddAdapter);
        searchAddAdapter.notifyDataSetChanged();
    }
}
