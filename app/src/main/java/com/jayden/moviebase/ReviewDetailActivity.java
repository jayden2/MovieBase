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
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jayden on 05-Jun-16.
 */
public class ReviewDetailActivity extends AppCompatActivity implements MoviesSetHolder {

    public static final String NEW_REVIEW_EXTRA = "New Review";
    public static final String NEW_REVIEW_CREATE_EXTRA = "Create Review";
    public static Context mContext;
    private static FragmentManager fragmentManager;
    private static String url = "https://moviebaseapi.herokuapp.com/api/movies/?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InVzZXIxMDBAZW1haWwuY29tIiwicGFzc3dvcmQiOiJhYmMxMjMiLCJpYXQiOjE0NjUzNTc4MTAsImV4cCI6MTQ2NTk2MjYxMH0.WC1vfx_6I7W2W92HsttoyQC4X1NE5AAvE1P3bURGBM8";

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


            //set review, score and user id to then post to database
            ReviewAddFragment.movieToPost.setReview(ReviewAddFragment.reviewText.getText().toString());
            //get rating and times it by 2 as starts are 0.5 to 5.0
            String tempScore = String.valueOf(ReviewAddFragment.reviewScore.getRating() * 2);
            //a rating has a . so we want to change that from fom
            tempScore = tempScore.replace(".","");
            //if the temp score start with 0 then remove that as well
            Log.d("Score: ", tempScore);

            ReviewAddFragment.movieToPost.setScore(Long.parseLong(tempScore));
            Log.d("Score Final: ", String.valueOf(ReviewAddFragment.movieToPost.getScore()));
            ReviewAddFragment.movieToPost.setUserId(2);

            postReview();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void postReview() {
        //perform search query
        JSONTasker tasker = new JSONTasker(this, "POST_REVIEW", ReviewAddFragment.movieToPost);
        tasker.execute(url);
    }

    @Override
    public void getMoviesFinished(ArrayList<MovieTitle> movieList) {
        //get back list from POST movie, ive set success under the movie title object review so i can get back the data and make sure its been posted
        if  (movieList != null) {
            if (movieList.get(0).getReview() == "true") {
                Toast.makeText(this, "Movie Review has been Posted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to post movie review", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed to post movie review", Toast.LENGTH_SHORT).show();
        }
    }
}
