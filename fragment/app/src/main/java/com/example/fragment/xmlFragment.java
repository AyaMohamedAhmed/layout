package com.example.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class xmlFragment extends Fragment {
    Button btncount;
    MainActivity activity;
    communicator comm;
    private int counter=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       if(savedInstanceState!=null){
           counter=savedInstanceState.getInt("counter");
       }
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_xml, container, false);
        btncount=view.findViewById(R.id.btn_count);
       
        btncount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                counter++;
                comm.setcount(counter);
            }}
        );
        return  view;
    }

public void onViewCreated(@NonNull View view , @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        comm=(communicator) getActivity();
    }

        public void onSaveInstanceState(@NonNull Bundle outState){
            xmlFragment.super.onSaveInstanceState(outState);
            outState.putInt("counter",counter);
        }
    }


