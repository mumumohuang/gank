package com.gyh.gank.mainpage;

import com.gyh.gank.base.BaseContract;

/**
 * Created by GYH on 2018/2/1.
 */

public interface MainContract {
    interface View extends BaseContract.BaseView{
        void setData(String msg);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getData(String id);
    }
}
