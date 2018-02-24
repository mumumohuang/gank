package com.gyh.gank.base;

/**
 * Created by GYH on 2018/2/1.
 */

public class BasePresenterIml<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T>{


    protected T mView;


    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
