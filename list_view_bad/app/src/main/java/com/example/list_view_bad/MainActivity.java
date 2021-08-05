package com.example.list_view_bad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=findViewById(R.id.list_view);
        //String [] days={"saturday","sunday","monday","tuesday","wednesday","thursday","friday"};
        day[] days = {
                new day("saturday","saturday description",R.drawable.one),
                new day("sunday","sunday description",R.drawable.two),
                new day("monday","monday description",R.drawable.three),
                new day("tuesday","tuesday description",R.drawable.four),
                new day("wednesday","wednesday description",R.drawable.five),
                new day("thursday","thursday description",R.drawable.six),
                new day("friday","friday description",R.drawable.seven),

        };
        //this, R.layout.custom_row ,R.id.text_name,days
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.custom_layout2,R.id.textView,days);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {
                Toast.makeText(MainActivity.this,(String)list.getAdapter().getItem(position),Toast.LENGTH_SHORT).show();

            }
        });
    }
}