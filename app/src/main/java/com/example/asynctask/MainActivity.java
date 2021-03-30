package com.example.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAsyncDownload;
    TextView tvAsync;
    ProgressBar pbAsync;
    ImageView ivAsync;
    String url = "https://gtechwebsolutions.com/assets/images/about/phone.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAsyncDownload = (Button) findViewById(R.id.btnAsyncDownload);
        tvAsync = (TextView) findViewById(R.id.tvAsync);
        pbAsync = (ProgressBar) findViewById(R.id.pbAsync);
        ivAsync = (ImageView) findViewById(R.id.ivAsync);

        btnAsyncDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Download download = new Download();
        download.execute(url);
    }

    public class Download extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvAsync.setText("Downloading.....");
            pbAsync.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ivAsync.setImageBitmap(bitmap);
            if (bitmap == null) {
                tvAsync.setText("Download Incomplete Check Internet Connection");

            } else {
                tvAsync.setText("Download Complete");
            }
            pbAsync.setVisibility(View.INVISIBLE);
            btnAsyncDownload.setEnabled(true);

        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            URL url;
            HttpURLConnection http;
            InputStream in;
            try {
                url = new URL(strings[0]);
                http = (HttpURLConnection) url.openConnection();
                in = http.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}