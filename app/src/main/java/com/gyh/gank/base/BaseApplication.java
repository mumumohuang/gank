package com.gyh.gank.base;

import android.app.Application;

import com.gyh.gank.net.HttpManager;

/**
 * Created by GYH on 2018/1/30.
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        HttpManager.init();
    }
}
