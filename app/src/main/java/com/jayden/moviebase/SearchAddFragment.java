package com.jayden.moviebase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jayden on 06-Jun-16.
 */
public class SearchAddFragment extends Fragment {

    public SearchAddFragment() {
        //Empty Constructor
    }
    //inflate search list fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_review_search_add, container, false);

        return fragmentLayout;
    }
}
