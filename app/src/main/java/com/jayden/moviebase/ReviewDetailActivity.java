package com.jayden.moviebase;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by Jayden on 05-Jun-16.
 */
public class ReviewDetailActivity extends AppCompatActivity {

    public static final String NEW_REVIEW_EXTRA = "New Review";
    public static final String NEW_REVIEW_CREATE_EXTRA = "create Review";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view
        setContentView(R.layout.activity_review_detail);

        //set fragment to put into this activity/view
        createAndAddFragment();
    }

    private void createAndAddFragment() {

        //create intent and fragment to launch from main acivity list fragment
        Intent intent = getIntent();
        MainActivity.ReviewFragmentToLaunch fragmentToLaunch =
                (MainActivity.ReviewFragmentToLaunch) intent.getSerializableExtra(MainActivity.MOVIE_FRAGMENT_TO_LOAD_EXTRA);

        //fragment manager and fragment transaction to select either the add, edit or view fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //select either edit, create or view fragment to ReviewDetailActivity
        switch (fragmentToLaunch) {
            case SEARCH:
                MovieSearchAddActivity movieSearchAddActivity = new MovieSearchAddActivity();
                setTitle("Search movie to review");

                Bundle bundle = new Bundle();
                bundle.putBoolean(NEW_REVIEW_EXTRA, true);
                movieSearchAddActivity.setArguments(bundle);

                fragmentTransaction.add(R.id.review_container, movieSearchAddActivity, "NOTE_SEARCH_CREATE_FRAGMENT");
                break;
            case CREATE:
                break;
            case EDIT:
                break;
            case VIEW:
                ReviewViewFragment reviewViewFragment = new ReviewViewFragment();
                setTitle("Movie Review");
                fragmentTransaction.add(R.id.review_container, reviewViewFragment, "REVIEW_VIEW FRAGMENT");
                break;
        }

        fragmentTransaction.commit();
    }
}
