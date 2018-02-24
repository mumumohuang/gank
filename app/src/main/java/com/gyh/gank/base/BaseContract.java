package com.gyh.gank.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by GYH on 2018/2/1.
 */

public interface BaseContract {

    interface BasePresenter<T extends BaseContract.BaseView> {

        void attachView(T view);

        void detachView();
    }

    interface BaseView{

        <T> LifecycleTransformer<T> bindToLife();
    }
}
