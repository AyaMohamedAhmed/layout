package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SeconedActivity extends AppCompatActivity {
    public static final String prefs_name="MyprefsFile";
    public static final String FILE_NAME="example";
    SharedPreferences s;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconed);
        Intent intent = getIntent();
        TextView txtMobileNumber=findViewById(R.id.txt_mobile_number);
        TextView txtMessage=findViewById(R.id.txt_message);

        s=getSharedPreferences(prefs_name, Context.MODE_PRIVATE);
        Button btnclose=findViewById(R.id.btn_close);

        Button btnsave=findViewById(R.id.btn_save);
        Button btnload=findViewById(R.id.btn_load);

        Button btnsave2=findViewById(R.id.btn_save2);
        Button btnload2=findViewById(R.id.btn_load2);

        Button btnsave3=findViewById(R.id.btn_save3);
        Button btnload3=findViewById(R.id.btn_load3);

        String mobileNumber = intent.getStringExtra(MainActivity.MOBILE_NUMBER);
        String message = intent.getStringExtra(MainActivity.MESSAGE);
        txtMobileNumber.setText(mobileNumber);
        txtMessage.setText(message);


        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=s.edit();
                editor.putString(MainActivity.MOBILE_NUMBER,txtMobileNumber.getText().toString());
                editor.putString(MainActivity.MESSAGE,txtMessage.getText().toString());
                 editor.commit();
                Log.i("ActivityLifeCycle","test");
                  }
        });


        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txtMobileNumber.setText(s.getString(MainActivity.MOBILE_NUMBER,"test"));
                txtMessage.setText(s.getString(MainActivity.MESSAGE,"TEST"));
                }
        });


        btnsave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileOutputStream fos=null;
                try {
                    String Phone=txtMobileNumber.getText().toString()+",";
                    String message=txtMessage.getText().toString()+",";
                    fos=openFileOutput(FILE_NAME,MODE_PRIVATE);
                    fos.write(Phone.getBytes());
                    fos.write(message.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis=null;
                try {
                    fis = openFileInput(FILE_NAME);

                    int c=-1;
                    StringBuffer buffer=new StringBuffer();
                    while ((c=fis.read()) !=-1){
                       // text=text+Character.toString((char)c);
                        buffer.append((char)c);
                    }
                    String [] Split=buffer.toString().split(",");
                    txtMobileNumber.setText(Split[0]);
                    txtMessage.setText(Split[1]);

                    } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                finally {
                    if(fis !=null){
                        try {
                            fis.close();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }


        });


        User u=new User(mobileNumber,message);

        btnsave3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  databaseAdapter.insertUser();
                if(!txtMobileNumber.getText().toString().isEmpty()&&!txtMessage.getText().toString().isEmpty()){
                    if(!txtMobileNumber.getText().toString().equals("")||!txtMessage.getText().toString().equals("")){
                        User u=new User(txtMobileNumber.getText().toString(),txtMessage.getText().toString());
                         DatabaseAdapter databaseAdapter=new DatabaseAdapter( SeconedActivity.this);
                        databaseAdapter.insertUser(u);
                       // txtMobileNumber.setText("");
                        //txtMessage.setText("");
                       // Toast.makeText(SeconedActivity.this,"insert",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        txtMobileNumber.setError("enter phone");
                        txtMessage.setError("enter message");

                    }

                }
            }
        });
        btnload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        DatabaseAdapter databaseAdapter=new DatabaseAdapter(SeconedActivity.this);
                       User user=databaseAdapter.getAllUsers();

                         txtMobileNumber.setText(user.getPhone());
                        txtMessage.setText(user.getMessage());


                }

        });

    }
}
