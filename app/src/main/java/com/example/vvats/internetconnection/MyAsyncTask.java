package com.example.vvats.internetconnection;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vvats on 20/03/17.
 */

public class MyAsyncTask extends AsyncTask<String, Void, String> {

    Context context;
    InputStream inputStream;
    BufferedReader buffer;

    public MyAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(10000);
            connection.setRequestMethod("GET");
            connection.connect();

            inputStream = connection.getInputStream();

            buffer = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder string = new StringBuilder();
            String line;

            while ((line = buffer.readLine()) != null) {
                string.append(line);
            }
            return string.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
