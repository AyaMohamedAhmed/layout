package com.example.list_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.list_view);
        //String [] days={"saturday","sunday","monday","tuesday","wednesday","thursday","friday"};
        day[] days = {
                new day("saturday", "saturday description", R.drawable.oneo),
                new day("sunday", "sunday description", R.drawable.twoo),
                new day("monday", "monday description", R.drawable.threeo),
                new day("tuesday", "tuesday description", R.drawable.fouro),
                new day("wednesday", "wednesday description", R.drawable.fiveo),
                new day("thursday", "thursday description", R.drawable.sixo),
                new day("friday", "friday description", R.drawable.seveno),

        };
          // ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.custom_row ,R.id.text_name,days);

         dayAdapter adapter = new dayAdapter(this,days);
         listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {
                Toast.makeText(MainActivity.this,list.getAdapter().getItem(position).toString(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}