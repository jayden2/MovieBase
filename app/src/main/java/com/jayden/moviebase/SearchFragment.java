package com.jayden.moviebase;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

                //clear input focus and close keyboard
                searchText.clearFocus();
                hideSoftKeyboard(getActivity());

                //do search
                searchOMDB(searchQuery);
            }
        });

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String searchQuery = searchText.getText().toString();

                    //clear input focus and close keyboard
                    hideSoftKeyboard(getActivity());
                    searchText.clearFocus();
                    //do search
                    searchOMDB(searchQuery);

                    handled = true;
                }
                return handled;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void searchOMDB(String search) {
        SearchAddListFragment searchFrag = new SearchAddListFragment();
        searchFrag.newSearchResults(search);
    }

    //close keyboard, because android doesnt know how to by itself
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
