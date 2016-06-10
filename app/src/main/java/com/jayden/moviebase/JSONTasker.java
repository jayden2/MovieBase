package com.jayden.moviebase;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
/**
 * Created by Jayden on 03-Jun-16.
 */
public class JSONTasker extends AsyncTask<String, String, ArrayList<MovieTitle>> {

    private static MovieTitle moviePost;
    private MoviesSetHolder movieAsyncListener;
    private String typeURL;
    private static DataOutputStream outputStream;
    private static String charset = "UTF-8";
    private static StringBuilder paramsBuilder;

    public JSONTasker(MoviesSetHolder movieAsyncListener, String typeURL, MovieTitle moviePost){
        this.movieAsyncListener = movieAsyncListener;
        this.typeURL = typeURL;
        this.moviePost = moviePost;
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



            //get movie from heroku API / database and sort the data into movie objects and return
            if (typeURL == "GET_MOVIES") {
                //connect to API
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(15000);
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

            } else if (typeURL == "POST_REVIEW") {
                //set connection to post, set input and output and that its a form, and set charset
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Accept-Charset", charset);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setConnectTimeout(15000);
                connection.setReadTimeout(10000);
                connection.connect();

                //build string and then send it it through POST and close
                paramsBuilder = buildPostReviewData();
                String paramsString = paramsBuilder.toString();
                outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(paramsString);
                outputStream.flush();
                outputStream.close();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder result = new StringBuilder();

                //read line and append from input, while more lines
                while((line = reader.readLine()) !=null) {
                    result.append(line);
                }

                //sort received JSON data
                JSONObject movieData = new JSONObject(result.toString());
                //create array list and movie object
                ArrayList<MovieTitle> movies = new ArrayList<>();
                MovieTitle movie = new MovieTitle();

                //set all the data to the object
                movie.setReview(movieData.getString("success"));
                movie.setTitle(movieData.getString("message"));

                movies.add(movie);
                //return success
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
        movieAsyncListener.getMoviesFinished(result);
    }

    private static StringBuilder buildPostReviewData(){

        StringBuilder stringBuilder = new StringBuilder();

        try {
            //build string with paramaters and vablues
            stringBuilder.append("title=" + URLEncoder.encode(moviePost.getTitle(), charset));
            stringBuilder.append("&rating=" + URLEncoder.encode(moviePost.getRating(), charset));
            stringBuilder.append("&description=" + URLEncoder.encode(moviePost.getDescription(), charset));
            stringBuilder.append("&score=" + URLEncoder.encode(String.valueOf(moviePost.getScore()), charset));
            stringBuilder.append("&review=" + URLEncoder.encode(moviePost.getReview(), charset));
            stringBuilder.append("&cover=" + URLEncoder.encode(moviePost.getCover(), charset));
            stringBuilder.append("&year=" + URLEncoder.encode(String.valueOf(moviePost.getYear()), charset));
            stringBuilder.append("&genre=" + URLEncoder.encode(moviePost.getGenre(), charset));
            stringBuilder.append("&user_id=" + URLEncoder.encode(String.valueOf(moviePost.getUserId()), charset));

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        //return built string
        return stringBuilder;
    }
}