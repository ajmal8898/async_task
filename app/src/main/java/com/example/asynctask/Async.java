package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

public class Async extends AsyncTask<Void, Integer, String> {
    TextView textViewAsyncResult;
    Button buttonAsyncstart;
    Context context;
    ProgressDialog progressDialog;

    public Async(MainActivity asyncExample, TextView textViewAsyncResult, Button buttonAsyncStart) {
        this.context = asyncExample;
        this.textViewAsyncResult = textViewAsyncResult;
        this.buttonAsyncstart = buttonAsyncStart;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Downloading in progress.....");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        int i = 0;
        while (i < 10) {
            try {
                Thread.sleep(1500);
                i++;
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Download Complete";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
        textViewAsyncResult.setText("Downloading in progress....");
    }

    @Override
    protected void onPostExecute(String result) {
        textViewAsyncResult.setText(result);
        buttonAsyncstart.setEnabled(true);
        progressDialog.hide();
    }
}