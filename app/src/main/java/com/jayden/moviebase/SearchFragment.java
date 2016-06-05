package com.jayden.moviebase;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jayden.moviebase.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.search_section, container, false);
        //create on click listener for search button
        Button searchButton = (Button) view.findViewById(R.id.searchButton);
        final EditText searchText = (EditText) view.findViewById(R.id.searchText);

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //when button is clicked start Async call method to OMDB and return JSON for the list view
                String searchQuery = searchText.getText().toString();
                Log.d("Clicked!", "Clicked");
                Log.d("Clicked!", searchQuery);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
        //when button is clicked start Async call method to OMDB and return JSON for the list view
}
