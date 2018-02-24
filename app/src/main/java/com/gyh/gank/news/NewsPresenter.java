package com.gyh.gank.news;

import com.gyh.gank.base.BasePresenterIml;
import com.gyh.gank.bean.NewsInfo;
import com.gyh.gank.bean.BaseResult;
import com.gyh.gank.net.HttpManager;
import com.gyh.gank.net.RxSchedulers;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by GYH on 2018/2/2.
 */

public class NewsPresenter extends BasePresenterIml<NewsContract.View> implements NewsContract.Presenter {
    @Override
    public void loadData(String type) {
        HttpManager.get().getAndroidInfo(type)
                .compose(mView.<BaseResult<List<NewsInfo>>>bindToLife())
                .compose(RxSchedulers.<BaseResult<List<NewsInfo>>>applySchedulers())
                .subscribe(new Observer<BaseResult<List<NewsInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<List<NewsInfo>> listBaseResult) {
                        mView.setData(listBaseResult.results);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
