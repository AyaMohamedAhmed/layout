package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity implements communicator{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //if(savedInstanceState==null) {
            javaFragment fragment = new javaFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.add(R.id.topfragment, fragment, "javaFragment");
            trans.commit();
       // }

    }

    @Override
    public void setcount(int v) {
        javaFragment java =(javaFragment)getSupportFragmentManager().findFragmentByTag("javaFragment");
        java.setTxtcount(v);

    }
}