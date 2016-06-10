package com.jayden.moviebase;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Jayden on 11-Jun-16.
 */
public class UserTasker extends AsyncTask<String, String, User> {

    private LoginSetHolder loginAsyncListener;
    private String typeURL;
    private String email;
    private String password;
    private static DataOutputStream outputStream;
    private static String charset = "UTF-8";
    private static StringBuilder paramsBuilder;

    public UserTasker(LoginSetHolder loginAsyncListener, String typeURL, String email, String password){
        this.loginAsyncListener = loginAsyncListener;
        this.typeURL = typeURL;
        this.email = email;
        this.password = password;
    }

    @Override
    protected User doInBackground(String... params) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line;

        try {
            Log.d("type url", typeURL);
            //basic URL to API
            URL url = new URL(params[0]);
            //set up connection to post information to authenticate user
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

            //get data from connection and set up reader
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder result = new StringBuilder();

            //read line and append from input, while more lines
            while((line = reader.readLine()) !=null) {
                result.append(line);
            }
            JSONObject userData = new JSONObject(result.toString());
            Log.d("user data", String.valueOf(userData));
            if (typeURL == "LOGIN") {

                //create user object
                User user = new User();

                //if all authenticated get user data and token
                if (userData.getBoolean("success") == true) {
                    Log.d("user data", "success");
                    user.setUserId(userData.getLong("id"));
                    Log.d("user data", String.valueOf(userData.getLong("id")));
                    user.setEmail(userData.getString("email"));
                    Log.d("user data", userData.getString("email"));
                    user.setUsername(userData.getString("username"));
                    user.setToken(userData.getString("token"));
                } else {
                    //if failed return that
                    user.setEmail("failed");
                    user.setUsername(userData.getString("message"));
                }

                return user;
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
    protected void onPostExecute(User result) {
        //set the result of the JSON call and sorting into an object array list for MainActivityListFragment to call
        loginAsyncListener.getLoginFinished(result);
    }

    private StringBuilder buildPostReviewData(){

        StringBuilder stringBuilder = new StringBuilder();

        try {
            //build string with paramaters and vablues
            stringBuilder.append("email=" + URLEncoder.encode(email, charset));
            stringBuilder.append("&password=" + URLEncoder.encode(password, charset));

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        //return built string
        return stringBuilder;
    }
}
