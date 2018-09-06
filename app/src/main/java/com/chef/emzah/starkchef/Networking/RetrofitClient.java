package com.chef.emzah.starkchef.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;
    private static final Object LOCK=new Object();

    public static Retrofit getInstance(){
        if (instance==null){
            synchronized (LOCK){
             instance=new Retrofit.Builder()
                      .baseUrl(Api.BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
            }
        }
        return instance;
    }
}
