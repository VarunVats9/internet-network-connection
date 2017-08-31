package com.example.vvats.internetconnection;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    MyAsyncTask myAsyncTask;
    MyInternetConnectionTask internetConnectionTask;
    ConnectivityManager connectivityManager;
    TextView textView;
    ImageView imageView;
    NetworkInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView2);
        imageView = (ImageView) findViewById(R.id.imageView);
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        info = connectivityManager.getActiveNetworkInfo();
    }

    public void doSomething(View view) {
        myAsyncTask = new MyAsyncTask(this);
        myAsyncTask.execute("http://www.google.com");
        try {
            textView.setText(myAsyncTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connectToInternet(View view) {
        if (info == null || !info.isConnected()) {
            Toast.makeText(this, "Internet is not connected", Toast.LENGTH_SHORT).show();
        } else {
            internetConnectionTask = new MyInternetConnectionTask(this);
            internetConnectionTask.execute("http://eagle.phys.utk.edu/guidry/android/figs/androidLogos/free-large-android256.jpg");
            try {
                imageView.setImageBitmap(internetConnectionTask.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
