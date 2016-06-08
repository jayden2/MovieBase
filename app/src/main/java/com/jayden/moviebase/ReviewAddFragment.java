package com.jayden.moviebase;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by jayden on 7/06/2016.
 */
public class ReviewAddFragment extends Fragment {

    public ReviewAddFragment() {
        //Empty Constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_review_add, container, false);

        //get frament views items ready to set
        TextView title = (TextView) fragmentLayout.findViewById(R.id.movieTitle);

        Intent intent = getActivity().getIntent();

        //set review view display template
        title.setText(intent.getExtras().getString(MainActivity.MOVIE_TITLE_EXTRA));

        return fragmentLayout;
    }
}
