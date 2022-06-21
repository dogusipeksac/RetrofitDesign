package com.example.retrofitdesign.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    String url = "https://jsonplaceholder.typicode.com/";
    private static Service myService;
    public static Service get(){
        if (myService==null){
            myService=new Service();
        }
        return myService;
    }

    public Retrofit getJsonUrlRetrofit(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
