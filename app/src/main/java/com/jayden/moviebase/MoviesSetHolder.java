package com.jayden.moviebase;

import java.util.ArrayList;

/**
 * Created by Jayden on 05-Jun-16.
 */
public interface MoviesSetHolder {
    //store API get from JSONTasker
    void getMoviesFinished(ArrayList<MovieTitle> movieList);
}
