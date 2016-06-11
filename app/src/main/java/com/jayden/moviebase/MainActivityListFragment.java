package com.jayden.moviebase;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivityListFragment extends ListFragment implements MoviesSetHolder {

    private static MovieAdapter movieAdapter;
    private static ArrayList<MovieTitle> movieList;
    private ProgressBar progressBar;
    private static String url = "https://moviebaseapi.herokuapp.com/api/movies/";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(getActivity(), movieList);
        setListAdapter(movieAdapter);

        progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FF29C9BB"),
                android.graphics.PorterDuff.Mode.MULTIPLY);

        //get all movies from API
        JSONTasker tasker = new JSONTasker(this, "GET_MOVIES", null);
        tasker.execute(url + "?token=" + LoginActivity.userObj.getToken());

        //set divider between fragments color and height
        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.darker_gray));
        getListView().setDividerHeight(1);

        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        launchReviewDetailActivity(MainActivity.ReviewFragmentToLaunch.VIEW, position);
    }

    //once the async JSON movie list get task is done, create and set the Movie adapter
    @Override
    public void getMoviesFinished(ArrayList<MovieTitle> resultList) {

        //when task is finished set to gone
        progressBar.setVisibility(View.GONE);

        if (resultList != null) {
            //clear empty list
            movieList.clear();
            movieList.addAll(resultList);
            movieAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getActivity(), "Failed to get movie reviews", Toast.LENGTH_SHORT).show();
        }
    }

    private void launchReviewDetailActivity(MainActivity.ReviewFragmentToLaunch view, int position) {

        //get movie information associated with clicked movie list_row item
        MovieTitle movie = (MovieTitle) getListAdapter().getItem(position);

        //create intent to launch reviewDetailActivity
        Intent intent = new Intent(getActivity(), ReviewDetailActivity.class);

        //set movie information to main activity extra's to give to reviewDetailActivity
        intent.putExtra(MainActivity.MOVIE_TITLE_EXTRA, movie.getTitle());
        intent.putExtra(MainActivity.MOVIE_REVIEW_EXTRA, movie.getReview());
        intent.putExtra(MainActivity.MOVIE_GENRE_EXTRA, movie.getGenre());
        intent.putExtra(MainActivity.MOVIE_RATING_EXTRA, movie.getRating());
        intent.putExtra(MainActivity.MOVIE_SCORE_EXTRA, Long.toString(movie.getScore()));
        intent.putExtra(MainActivity.MOVIE_YEAR_EXTRA, Long.toString(movie.getYear()));
        intent.putExtra(MainActivity.MOVIE_COVER_EXTRA, movie.getCover());

        switch(view) {
            case EDIT:
                intent.putExtra(MainActivity.MOVIE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.ReviewFragmentToLaunch.EDIT);
                break;
            case VIEW:
                intent.putExtra(MainActivity.MOVIE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.ReviewFragmentToLaunch.VIEW);
                break;
        }
        //open ReviewDetailActivity with either view or edit
        startActivity(intent);

    }
}