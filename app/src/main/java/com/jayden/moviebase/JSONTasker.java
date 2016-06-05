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
 * Created by Jayden on 03-Jun-16.
 */
public class JSONTasker extends AsyncTask<String, String, ArrayList<MovieTitle>> {

    private MoviesSetHolder movieAsyncListener;

    public JSONTasker(MoviesSetHolder movieAsyncListener){
        this.movieAsyncListener = movieAsyncListener;
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

            //sort received JSON data
            JSONArray JSONdata = new JSONArray(result.toString());

            ArrayList<MovieTitle> movies = new ArrayList<>();

            for (int i = 0; i < JSONdata.length(); i++) {
                //set all the values to the movieTitle object
                JSONObject movieData = JSONdata.getJSONObject(i);
                MovieTitle movie = new MovieTitle();

                movie.setMovieId(movieData.getLong("id"));
                movie.setTitle(movieData.getString("title"));
                movie.setRating(movieData.getString("rating"));
                movie.setScore(movieData.getLong("score"));
                movie.setDescription(movieData.getString("description"));
                movie.setGenre(movieData.getString("genre"));
                movie.setReview(movieData.getString("review"));
                movie.setCover(movieData.getString("cover"));
                movie.setYear(movieData.getLong("year"));
                movie.setUserId(movieData.getLong("user_id"));
                movie.setUpdatedAt(movieData.getString("updated_at"));
                movie.setCreatedAt(movieData.getString("created_at"));

                //adding object to array list
                movies.add(movie);
            }

            return movies;

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
        movieAsyncListener.getMoviesFinished(result);

    }
}