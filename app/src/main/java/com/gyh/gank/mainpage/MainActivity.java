package com.gyh.gank.mainpage;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.gyh.gank.R;
import com.gyh.gank.base.BaseActivity;
import com.gyh.gank.base.BaseFragment;
import com.gyh.gank.news.NewsFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.m_main_tal_layout)
    TabLayout mMainTalLayout;
    @BindView(R.id.m_main_view_pager)
    ViewPager mMainViewPager;
    List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragments.add(NewsFragment.newInstance("all"));
        fragments.add(NewsFragment.newInstance("Android"));
        fragments.add(NewsFragment.newInstance("iOS"));
        fragments.add(NewsFragment.newInstance("拓展资源"));
        fragments.add(NewsFragment.newInstance("前端"));
        fragments.add(NewsFragment.newInstance("福利"));
        mMainViewPager.setOffscreenPageLimit(7);
        mMainViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragments));
        mMainTalLayout.setupWithViewPager(mMainViewPager);
        mPresenter.getData("");
    }

    @Override
    protected void getPresenter() {
        mPresenter = new MainPresenter();
    }


    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public void setData(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
