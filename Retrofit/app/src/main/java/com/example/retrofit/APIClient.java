package com.example.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private  static Retrofit retrofit=null;
    public static final String Base_url="https://jsonplaceholder.typicode.com/";
    public static  Retrofit getClient1(){
        if(retrofit ==null){
            retrofit = new Retrofit.Builder().baseUrl(Base_url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
