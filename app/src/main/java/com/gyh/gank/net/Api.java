package com.gyh.gank.net;

import com.gyh.gank.bean.NewsInfo;
import com.gyh.gank.bean.BaseResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("data/{type}/10/1")
    Observable<BaseResult<List<NewsInfo>>> getAndroidInfo(@Path("type")String type);


}