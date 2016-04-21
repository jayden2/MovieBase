package com.jayden.moviebase;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jayden.moviebase.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivitySearchFragment extends Fragment {


    public MainActivitySearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.search_section, container, false);
    }

}
