package com.jayden.moviebase;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jayden on 03-Jun-16.
 */
public class JSONTasker extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {

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
            StringBuilder buffer = new StringBuilder();

            //read line and append from input, while more lines
            while((line = reader.readLine()) !=null) {
                buffer.append(line);
            }

            //return data
            return buffer.toString();

            //catch any errors from connection to API or errors from reader
        } catch (IOException e) {
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
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}