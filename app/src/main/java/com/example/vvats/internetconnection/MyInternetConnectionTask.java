package com.example.vvats.internetconnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vvats on 21/03/17.
 */

public class MyInternetConnectionTask extends AsyncTask<String, Void, Bitmap> {

    Context context;
    InputStream inputStream;

    public MyInternetConnectionTask(Context context) {
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(10000);
            connection.setRequestMethod("GET");
            connection.connect();
            inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
