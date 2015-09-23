package com.qingyang.webviewproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by QingYang on 15/9/22.
 */
public class LaunchActivity extends AppCompatActivity   {
    
    private SharedPreferences sp;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        sp = this.getSharedPreferences("launch", Context.MODE_PRIVATE);

    }

    @Override protected void onResume() {
        super.onResume();

        AsyncTask<Void, Void, Void> loading = new AsyncTask<Void, Void, Void>() {

            @Override protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                finish();
            }
        };

        loading.execute();
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }
}
