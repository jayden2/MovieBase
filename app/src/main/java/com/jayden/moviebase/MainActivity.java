package com.jayden.moviebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static final String MOVIE_ID_EXTRA = "com.jayden.moviebase.Identifier";
    public static final String MOVIE_TITLE_EXTRA = "com.jayden.moviebase.Title";
    public static final String MOVIE_REVIEW_EXTRA = "com.jayden.moviebase.Review";
    public static final String MOVIE_SCORE_EXTRA = "com.jayden.moviebase.Score";
    public static final String MOVIE_GENRE_EXTRA = "com.jayden.moviebase.Genre";
    public static final String MOVIE_YEAR_EXTRA = "com.jayden.moviebase.Year";
    public static final String MOVIE_RATING_EXTRA = "com.jayden.moviebase.Rating";
    public static final String MOVIE_COVER_EXTRA = "com.jayden.moviebase.Cover";
    public static final String MOVIE_FRAGMENT_TO_LOAD_EXTRA = "com.jayden.moviebase.Fragment_To_Load";

    public enum ReviewFragmentToLaunch { VIEW, EDIT, CREATE }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_review) {
            return true;
        }

        if (id == R.id.action_refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
