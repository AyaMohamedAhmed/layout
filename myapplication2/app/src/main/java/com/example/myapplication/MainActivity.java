 package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
public static final String MOBILE_NUMBER="number";
public static final String MESSAGE="message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editNumber = findViewById(R.id.edtMobileNumber);
        EditText editMessage = findViewById(R.id.edtMessage);
        Button btnNext=findViewById(R.id.btn_next);
        Button btnclose=findViewById(R.id.btn_close);



        btnclose.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent( MainActivity.this,SeconedActivity.class);

                outIntent.putExtra(MOBILE_NUMBER,editNumber.getText().toString());
                outIntent.putExtra(MESSAGE,editMessage.getText().toString());
                startActivity(outIntent);



            }
        });

    }
}