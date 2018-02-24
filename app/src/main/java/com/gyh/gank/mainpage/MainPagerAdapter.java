package com.gyh.gank.mainpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gyh.gank.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GYH on 2018/1/30.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    // 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    List<BaseFragment> fragments = new ArrayList<>();
    String[] titles = {"综合","Android","iOS","拓展资源","前端","福利"};
    public MainPagerAdapter(FragmentManager fm,List<BaseFragment> fragments) {
        super(fm);
        this.fragments.clear();
        this.fragments.addAll(fragments);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
