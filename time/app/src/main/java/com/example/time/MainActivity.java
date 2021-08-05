package com.example.time;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    BoundService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=findViewById(R.id.button);
        TextView txtview=findViewById(R.id.text_view);

        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                BoundService.mybinder binder=(BoundService.mybinder) iBinder;
                service=binder.getService();

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent=new Intent(getApplicationContext(),BoundService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtview.setText(service.getDate());


            }
        });



    }
}