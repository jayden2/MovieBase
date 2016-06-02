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
            JSONArray JSONdata = new JSONArray(result);
            ArrayList<MovieTitle> movies = new ArrayList<MovieTitle>();

            for (int i = 0; i < JSONdata.length(); i++) {
                JSONObject movie = JSONdata.getJSONObject(i);
                movies.add(new MovieTitle(
                        movie.getLong("id"),
                        movie.getString("title"),
                        movie.getString("rating"),
                        movie.getLong("score"),
                        movie.getString("description"),
                        "PG",
                        movie.getString("review"),
                        movie.getString("cover"),
                        movie.getLong("year"),
                        movie.getLong("user_id"),
                        movie.getString("updated_at"),
                        movie.getString("created_at")));
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
        super.onPostExecute(result);
    }
}