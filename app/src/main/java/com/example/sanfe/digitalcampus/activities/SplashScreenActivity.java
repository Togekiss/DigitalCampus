package com.example.sanfe.digitalcampus.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.SharedPreferencesManager;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.json.GsonManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        mProgress = (ProgressBar) findViewById(R.id.progress_bar);
        new SharedPreferencesManager(getApplicationContext());
        SharedPreferencesManager.loadSingleton(getResources());

        Timer T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mProgressStatus += 1;
                    }
                });
            }
        }, 0, 20);


        new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 100) {

                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }
                    });
                }

                //mirar demenar permisos de la galeria de fotos
                //marshmallow t'obliga a fer-ho

                if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("rememberMe", true)) {
                    Intent intent = new Intent (getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent (getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }).start();
    }
}
