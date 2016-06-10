package com.jayden.moviebase;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Jayden on 06-Jun-16.
 */
public class OMDBTasker extends AsyncTask<String, String, ArrayList<MovieTitle>> {

    private final String typeURL;
    private SearchSetHolder searchAsyncTasker;

    public OMDBTasker(SearchSetHolder searchAsyncTasker, String typeURL){
        this.searchAsyncTasker = searchAsyncTasker;
        this.typeURL = typeURL;
    }

    @Override
    protected ArrayList<MovieTitle> doInBackground(String... params) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line;

        try {
            //basic URL to API
            URL url = new URL(params[0]);

            //set up URL connection and open open connection
            connection = (HttpURLConnection) url.openConnection();
            //connect to API
            connection.connect();

            //get data from connection and set up reader
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder result = new StringBuilder();

            //read line and append from input, while more lines
            while((line = reader.readLine()) !=null) {
                result.append(line);
            }
            JSONObject JSONObject = new JSONObject(result.toString());

            //search object, search has json object where as through imdb id its only an array
            //sort received JSON data
            if (typeURL == "SEARCH") try {
                JSONArray JSONdata = JSONObject.getJSONArray("Search");

                ArrayList<MovieTitle> movies = new ArrayList<>();

                for (int i = 0; i < JSONdata.length(); i++) {
                    //set all the values to the movieTitle object
                    JSONObject movieData = JSONdata.getJSONObject(i);
                    MovieTitle movie = new MovieTitle();

                    //set object specific data for search list
                    movie.setTitle(movieData.getString("Title"));
                    movie.setYear(movieData.getLong("Year"));
                    movie.setImdb(movieData.getString("imdbID"));

                    //adding object to array list
                    movies.add(movie);
                }

                return movies;
            } catch (Exception e) {
                e.printStackTrace();
            }
            else if (typeURL == "IMDB") {

                //create array list and movie object
                ArrayList<MovieTitle> movies = new ArrayList<>();
                MovieTitle movie = new MovieTitle();

                //set all the data to the object
                movie.setTitle(JSONObject.getString("Title"));
                movie.setRating(JSONObject.getString("Rated"));
                movie.setDescription(JSONObject.getString("Plot"));
                movie.setGenre(JSONObject.getString("Genre"));
                movie.setCover(JSONObject.getString("Poster"));
                movie.setYear(JSONObject.getLong("Year"));

                //add object to array list
                movies.add(movie);

                return movies;
            }

        //catch any errors from connection to API or errors from reader
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                //disconnect from API
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    //close reader (try and if problems catch it
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //if errors return null
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieTitle> result) {
        //set the result of the JSON call and sorting into an object array list for MainActivityListFragment to call
        searchAsyncTasker.getSearchFinished(result);
    }

}
