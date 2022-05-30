package com.program.customizeview.uitls;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final RetrofitManager outInstance= new RetrofitManager();
    private final Retrofit mRetrofit;

    public static RetrofitManager getInstance(){
        return outInstance;
    }

    private RetrofitManager(){
        //创建retorfit
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://cn.bing.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }
}
