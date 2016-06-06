package com.jayden.moviebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jayden on 06-Jun-16.
 */
public class SearchAddFragment extends Fragment {

    public SearchAddFragment() {
        //Empty Constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_review_search_add, container, false);

        return fragmentLayout;
    }
}
