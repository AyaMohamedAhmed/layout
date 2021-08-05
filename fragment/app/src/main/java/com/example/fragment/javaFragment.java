package com.example.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class javaFragment extends Fragment {

TextView txtcount;
int savecounter=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_java, container, false);
        txtcount=view.findViewById(R.id.txt_count);
        if(savedInstanceState !=null){
            savecounter=savedInstanceState.getInt("counter");
            txtcount.setText(savecounter+"");
        }
        return  view;
    }
    public void setTxtcount(int count){
        savecounter=count;
        txtcount.setText(count+" ");
    }
    public void onSaveInstanceState(@NonNull Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putInt("counter",savecounter);
    }
}