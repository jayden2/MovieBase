package com.jayden.moviebase;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jayden on 06-Jun-16.
 */
public class SearchAddListFragment extends ListFragment implements SearchSetHolder {

    private static SearchAddAdapter searchAddAdapter;
    private static ArrayList<MovieTitle> searchArray;
    private static ProgressBar progressBar;
    private static TextView textView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //link to progress bar on search results, and colour it, make sure its invisible
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.GONE);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FF29C9BB"),
                android.graphics.PorterDuff.Mode.MULTIPLY);

        textView = (TextView) getActivity().findViewById(R.id.no_results);
        textView.setVisibility(View.GONE);

        //create empty array list to then later display results
        searchArray = new ArrayList<>();
        //create adapter to and set it the empty search array
        searchAddAdapter = new SearchAddAdapter(getActivity(), searchArray);
        setListAdapter(searchAddAdapter);


        //set divider between fragments color and height
        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.darker_gray));
        getListView().setDividerHeight(1);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //launch create review fragment from row clicked
        launchReviewDetailActivity(MainActivity.ReviewFragmentToLaunch.CREATE, position);
    }

    public void newSearchResults(String search) {
        //clear array adapater for new results
        searchArray.clear();
        //show progress bar
        progressBar.setVisibility(View.VISIBLE);
        //perform search query
        OMDBTasker tasker = new OMDBTasker(this, "SEARCH");
        tasker.execute("http://www.omdbapi.com/?s=" + search + "&type=movie");
    }
    //once the async JSON movie list get task is done, create and set the Movie adapter
    @Override
    public void getSearchFinished(ArrayList<MovieTitle> searchList) {
        if (searchList != null && searchList.size() > 0) {
            //hide progress bar
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            //clear default empty array list
            searchArray.clear();
            //add searchlist to search array
            searchArray.addAll(searchList);
            //notify adapter of change
            searchAddAdapter.notifyDataSetChanged();
        } else {
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            //Toast.makeText(getActivity(), "Failed to retrieve search results", Toast.LENGTH_SHORT).show();
        }
    }

    private void launchReviewDetailActivity(MainActivity.ReviewFragmentToLaunch view, int position) {

        //get movie information associated with clicked movie list_row item
        MovieTitle movie = (MovieTitle) getListAdapter().getItem(position);

        //create intent to launch reviewDetailActivity
        Intent intent = new Intent(getActivity(), ReviewDetailActivity.class);

        //set movie information to main activity extra's to give to reviewDetailActivity
        intent.putExtra(MainActivity.MOVIE_TITLE_EXTRA, movie.getTitle());
        intent.putExtra(MainActivity.MOVIE_YEAR_EXTRA, Long.toString(movie.getYear()));
        intent.putExtra(MainActivity.MOVIE_IMDB_EXTRA, movie.getImdb());

        intent.putExtra(MainActivity.MOVIE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.ReviewFragmentToLaunch.CREATE);

        //open ReviewDetailActivity with either view or edit
        startActivity(intent);
    }
}
