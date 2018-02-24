package com.gyh.gank.news;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyh.gank.R;
import com.gyh.gank.base.BaseFragment;
import com.gyh.gank.bean.NewsInfo;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by GYH on 2018/1/30.
 */

public class NewsFragment extends BaseFragment<NewsContract.Presenter> implements NewsContract.View {
    @BindView(R.id.m_news_recylerview)
    RecyclerView mNewsRecylerview;
    Unbinder unbinder;
    List<NewsInfo> data = new ArrayList<>();
    private NewsRvAdapter newsRvAdapter;
    private String type;

    public static NewsFragment newInstance(String type) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        type = arguments.getString("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.fragment_news, null);
        unbinder = ButterKnife.bind(this, view);
        mNewsRecylerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        newsRvAdapter = new NewsRvAdapter(data, getActivity(), type);
        mNewsRecylerview.setAdapter(newsRvAdapter);
        mNewsRecylerview.addItemDecoration(new MyDivider(10));
        if (mNewsRecylerview.getRecycledViewPool() != null) {
            mNewsRecylerview.getRecycledViewPool().setMaxRecycledViews(0, 10);
        }
        if (type != null) {
            mPresenter.loadData(type);
        }
        return view;
    }


    @Override
    protected void getPresenter() {
        mPresenter = new NewsPresenter();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setData(List<NewsInfo> datas) {
        data.clear();
        data.addAll(datas);
        newsRvAdapter.setData(data);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }


    private class MyDivider extends RecyclerView.ItemDecoration {
        private int space;
        private Rect mRect;
        private final Paint mPaint;

        public MyDivider(int space) {
            this.space = space;
            mPaint = new Paint();
            mPaint.setColor(Color.GREEN);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
//            outRect.left = space;
//            outRect.right = space;
//            outRect.bottom = space;
            outRect.set(0,0,0,space);
            mRect = outRect;
//            if (parent.getChildPosition(view) == 0)
//                outRect.top = space;
        }


        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            if (c != null && mRect != null)
                c.drawRect(mRect, mPaint);
//            super.onDraw(c, parent, state);
        }
    }
}
