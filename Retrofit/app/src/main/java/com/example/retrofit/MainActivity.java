package com.example.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnPrevious, btnNext;
      TextView txId, txUserId, txBody, txTitle;
      int currentIndex = 0;
      post[] posts;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          btnPrevious = findViewById(R.id.btn_previous);
          btnNext = findViewById(R.id.btn_next);
          txBody = findViewById(R.id.textView);
          txId = findViewById(R.id.textView2);
          txUserId = findViewById(R.id.textView3);
          txTitle = findViewById(R.id.textView4);


          APIInterface apiInterface = APIClient.getClient1().create(APIInterface.class);
          Call<post[]> call = apiInterface.getPosts();
          call.enqueue(new Callback<post[]>() {
              @Override
              public void onResponse(Call<post[]> call, Response<post[]> response) {
                  posts = response.body();
                  setTextViewData(0);

              }

              @Override
              public void onFailure(Call<post[]> call, Throwable t) {

              }
          });
          btnPrevious.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  if (currentIndex > 0 && currentIndex < posts.length) {
                      setTextViewData(--currentIndex);
                  }


              }
          });
          btnNext.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  if (currentIndex >= 0 && currentIndex < posts.length) {

                      setTextViewData(++currentIndex);
                  }


              }
          });
      }
          private  void setTextViewData(int i)
          {
              txId.setText("Id: "+ posts[i].getId());
              txUserId.setText("User Id: "+posts[i].getUserId());
              txTitle.setText("Title: "+posts[i].getTitle());
              txBody.setText("Body: "+posts[i].getBody());
          }
      }
