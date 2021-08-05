package com.example.postapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    Button btnPrevious, btnNext;
    TextView tvId, tvUserId, tvBody, tvTitle;
    Handler handler;
    JSONArray jsonArray;
    JSONObject jsonObject;
    List<postModel> postsList= new ArrayList<postModel>();
    postModel postModel;
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrevious = findViewById(R.id.previous_button);
        btnNext = findViewById(R.id.next_button);
        tvBody = findViewById(R.id.body_textview);
        tvId = findViewById(R.id.id_textview);
        tvUserId = findViewById(R.id.userId_textview);
        tvTitle = findViewById(R.id.title_textview);


        try{
            download("https://jsonplaceholder.typicode.com/posts");

        }
        catch (Exception e){
           e.toString();
        }
                handler = new Handler(getMainLooper()){

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                setTextViewData(0);
            }
        };

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentIndex > 0 && currentIndex < postsList.size())
                {

                    setTextViewData(--currentIndex);
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentIndex >= 0 && currentIndex < postsList.size())
                {

                    setTextViewData(++currentIndex);
                }


            }
        });
    }
    private void download(String url)
    {
        jsonArray = null;

        new Thread(){
            @Override
            public void run() {
                super.run();

                InputStream inputStream;
                HttpsURLConnection httpsURLConnection = null;

                try {

                    URL urlObject = new URL(url);

                    httpsURLConnection = (HttpsURLConnection) urlObject.openConnection();
                    inputStream = httpsURLConnection.getInputStream();
                    String result = convertInputStreamToString(inputStream);
                    jsonArray = new JSONArray(result);
                    jsonObject = (JSONObject) jsonArray.get(0);
                    jsonObject = (JSONObject) jsonArray.get(0);
                    postsList.add(new postModel(jsonObject.getInt("userId"),
                            jsonObject.getInt("id"),
                            jsonObject.getString("title"),
                            jsonObject.getString("body")
                    ));

                    handler.sendEmptyMessage(0);

                    for(int i = 1;  i< jsonArray.length(); i++)
                    {
                        jsonObject = (JSONObject) jsonArray.get(i);
                        postsList.add(new postModel(jsonObject.getInt("userId"),
                                jsonObject.getInt("id"),
                                jsonObject.getString("title"),
                                jsonObject.getString("body")
                        ));
                    }

                    System.out.println("body form array: "+postsList.get(0).getBody());

                }catch (Exception e )
                {
                     e.toString();


                }
                finally {
                    inputStream = null;
                    try{
                        httpsURLConnection.disconnect();

                    }
                    catch (Exception e)
                    {
                        e.toString();
                    }
                }


            }
        }.start();

    }
 private  void setTextViewData(int i)
    {
        tvId.setText("Id: "+ postsList.get(i).getId());
        tvUserId.setText("User Id: "+postsList.get(i).getUserId() );
        tvTitle.setText("Title: "+postsList.get(i).getTitle());
        tvBody.setText("Body: "+postsList.get(i).getBody());
    }
  private static String convertInputStreamToString(InputStream is) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString();



    }


}