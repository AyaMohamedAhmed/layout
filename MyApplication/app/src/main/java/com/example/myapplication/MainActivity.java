package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("ActivityLifeCycle","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ActivityLifeCycle","onStart");

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i("ActivityLifeCycle","onResume");



    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ActivityLifeCycle","onpause");


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityLifeCycle","onStop");


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ActivityLifeCycle","onRestart");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ActivityLifeCycle","onDestroy");

    }
}