package com.jayden.moviebase;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Jayden on 05-Jun-16.
 */
public class ReviewDetailActivity extends AppCompatActivity {

    public static final String NEW_REVIEW_EXTRA = "New Review";
    public static final String NEW_REVIEW_CREATE_EXTRA = "Create Review";
    public static Context mContext;
    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view
        setContentView(R.layout.activity_review_detail);
        // Set the context to be used by fragments
        mContext = getApplicationContext();
        //set fragment to put into this activity/view
        createAndAddFragment();
    }

    private void createAndAddFragment() {

        //create intent and fragment to launch from main acivity list fragment
        Intent intent = getIntent();
        MainActivity.ReviewFragmentToLaunch fragmentToLaunch =
                (MainActivity.ReviewFragmentToLaunch) intent.getSerializableExtra(MainActivity.MOVIE_FRAGMENT_TO_LOAD_EXTRA);

        //fragment manager and fragment transaction to select either the add, edit or view fragment
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();

        //select either edit, create or view fragment to ReviewDetailActivity
        switch (fragmentToLaunch) {
            case SEARCH:
                SearchAddFragment searchAddFragment = new SearchAddFragment();
                setTitle("Search Movie to Review");

                bundle.putBoolean(NEW_REVIEW_EXTRA, true);
                searchAddFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.review_container, searchAddFragment, "REVIEW_SEARCH_CREATE_FRAGMENT");
                break;
            case CREATE:
                ReviewAddFragment reviewAddFragment = new ReviewAddFragment();
                setTitle("Create Review");

                bundle.putBoolean(NEW_REVIEW_CREATE_EXTRA, true);
                reviewAddFragment.setArguments(bundle);

                fragmentTransaction.add(R.id.review_container, reviewAddFragment, "REVIEW_ADD_CREATE_FRAGMENT");
                break;
            case EDIT:
                break;
            case VIEW:
                ReviewViewFragment reviewViewFragment = new ReviewViewFragment();
                setTitle("Movie Review");
                fragmentTransaction.add(R.id.review_container, reviewViewFragment, "REVIEW_VIEW_FRAGMENT");
                break;
        }

        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
        // Get the Review View and Search Fragment
        ReviewViewFragment rFragment = (ReviewViewFragment) fragmentManager.findFragmentByTag("REVIEW_VIEW_FRAGMENT");
        SearchAddFragment sFragment = (SearchAddFragment) fragmentManager.findFragmentByTag("REVIEW_SEARCH_CREATE_FRAGMENT");
        // If Review view fragment is not null and is Visible, hide the save review action
        if(rFragment != null && rFragment.isVisible()){
            menu.findItem(R.id.action_save).setVisible(false);
        }
        // If Search view fragment is not null and is Visible, hide the save review action
        if(sFragment != null && sFragment.isVisible()){
            menu.findItem(R.id.action_save).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            Toast.makeText(this, "Save Not Ready :)", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
