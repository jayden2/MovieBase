package com.jayden.moviebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Jayden on 05-Jun-16.
 */
public class ReviewViewFragment extends Fragment {

    public ReviewViewFragment() {
        //empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //inflate fragment to templates layout
        View fragmentLayout = inflater.inflate(R.layout.fragment_review_view, container, false);

        //get frament views items ready to set
        TextView title = (TextView) fragmentLayout.findViewById(R.id.viewMovieTitle);
        TextView review = (TextView) fragmentLayout.findViewById(R.id.viewMovieReview);
        TextView year = (TextView) fragmentLayout.findViewById(R.id.viewMovieYear);
        TextView genre = (TextView) fragmentLayout.findViewById(R.id.viewMovieGenre);
        TextView rating = (TextView) fragmentLayout.findViewById(R.id.viewMovieRating);
        TextView score = (TextView) fragmentLayout.findViewById(R.id.viewMovieScore);
        ImageView cover = (ImageView) fragmentLayout.findViewById(R.id.viewReviewCover);

        Intent intent = getActivity().getIntent();

        //display image
        ImageLoader.getInstance().displayImage(intent.getExtras()
                .getString(MainActivity.MOVIE_COVER_EXTRA), cover);

        //set review view display template
        title.setText(intent.getExtras().getString(MainActivity.MOVIE_TITLE_EXTRA));
        review.setText(intent.getExtras().getString(MainActivity.MOVIE_REVIEW_EXTRA));
        year.setText(intent.getExtras().getString(MainActivity.MOVIE_YEAR_EXTRA));
        genre.setText(intent.getExtras().getString(MainActivity.MOVIE_GENRE_EXTRA));
        rating.setText(intent.getExtras().getString(MainActivity.MOVIE_RATING_EXTRA));
        score.setText(intent.getExtras().getString(MainActivity.MOVIE_SCORE_EXTRA));

        return fragmentLayout;
    }

}
