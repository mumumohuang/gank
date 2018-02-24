package com.gyh.gank.news;

import com.gyh.gank.base.BaseContract;
import com.gyh.gank.bean.NewsInfo;

import java.util.List;

/**
 * Created by GYH on 2018/2/2.
 */

public interface NewsContract {
    interface Presenter extends BaseContract.BasePresenter<View>{
        void loadData(String type);
    }
    interface View extends BaseContract.BaseView{
        void setData(List<NewsInfo> data);
    }
}
