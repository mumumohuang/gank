package com.gyh.gank.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GYH on 2018/1/30.
 */

public class HttpManager {
    private static HttpManager instance;
    private Retrofit mRetrofit;
    private static Api api;

    public static HttpManager init() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;

    }
    public HttpManager() {
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = mRetrofit.create(Api.class);
    }
    public static Api get(){

        return api;
    }
}
