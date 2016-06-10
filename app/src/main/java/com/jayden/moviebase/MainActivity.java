package com.jayden.moviebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends AppCompatActivity {

    public static final String MOVIE_ID_EXTRA = "com.jayden.moviebase.Identifier";
    public static final String MOVIE_TITLE_EXTRA = "com.jayden.moviebase.Title";
    public static final String MOVIE_REVIEW_EXTRA = "com.jayden.moviebase.Review";
    public static final String MOVIE_DESCRIPTION_EXTRA = "com.jayden.moviebase.Description";
    public static final String MOVIE_SCORE_EXTRA = "com.jayden.moviebase.Score";
    public static final String MOVIE_GENRE_EXTRA = "com.jayden.moviebase.Genre";
    public static final String MOVIE_YEAR_EXTRA = "com.jayden.moviebase.Year";
    public static final String MOVIE_RATING_EXTRA = "com.jayden.moviebase.Rating";
    public static final String MOVIE_COVER_EXTRA = "com.jayden.moviebase.Cover";
    public static final String MOVIE_IMDB_EXTRA = "com.jayden.moviebase.Imdb";
    public static final String USER_TOKEN_EXTRA = "com.jayden.moviebase.Token";
    public static final String USER_USERNAME_EXTRA = "com.jayden.moviebase.Username";
    public static final String USER_EMAIL_EXTRA = "com.jayden.moviebase.Email";
    public static final String USER_ID_EXTRA = "com.jayden.moviebase.UserId";
    public static final String MOVIE_FRAGMENT_TO_LOAD_EXTRA = "com.jayden.moviebase.Fragment_To_Load";

    public enum ReviewFragmentToLaunch { VIEW, EDIT, CREATE, SEARCH }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        //git https://github.com/nostra13/Android-Universal-Image-Loader
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
        .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions)
        .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

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
            Intent intent = new Intent(this, ReviewDetailActivity.class);
            intent.putExtra(MainActivity.MOVIE_FRAGMENT_TO_LOAD_EXTRA, ReviewFragmentToLaunch.SEARCH);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
