package com.example.retrofit;

//import android.telecom.Call;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("Post")
    Call<post[]>getPosts();
}
//public interface APIInterface {
  //  @GET("posts")
    //Call<post[]> getPosts();


//}
