package com.jayden.moviebase;

import java.util.ArrayList;

public interface SearchSetHolder {
    //store OMDB API get
    void getSearchFinished(ArrayList<MovieTitle> searchList);
}
