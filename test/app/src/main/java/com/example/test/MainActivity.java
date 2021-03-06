package com.example.test;

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
        Log.i("ActivityLifeCycle","onstart");

    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ActivityLifeCycle","onresume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ActivityLifeCycle","onpause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityLifeCycle","onstop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ActivityLifeCycle","ondestroy");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ActivityLifeCycle","onrestart");

    }
}