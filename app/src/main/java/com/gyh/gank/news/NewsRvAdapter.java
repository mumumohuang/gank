package com.gyh.gank.news;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyh.gank.GlideApp;
import com.gyh.gank.R;
import com.gyh.gank.bean.NewsInfo;
import com.gyh.gank.util.Utils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GYH on 2018/1/30.
 */

public class NewsRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = NewsRvAdapter.class.getSimpleName();
    private List<NewsInfo> data = new ArrayList<>();
    Activity mActivity;
    private String type;
    private final int width;
    private final RxPermissions rxPermissions;

    public NewsRvAdapter(List<NewsInfo> data, Activity mActivity,String type) {
        this.data.clear();
        this.data.addAll(data);
        this.type = type;
        this.mActivity = mActivity;
        rxPermissions = new RxPermissions(mActivity);

        width = mActivity.getWindowManager().getDefaultDisplay().getWidth();
    }

    public void setData(List<NewsInfo> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new MyWelfareHolder(mActivity);
        if (type.equals("福利")){
            return new MyWelfareHolder(mActivity);
        }else {
            return new MyHolder(mActivity);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (data.size() == 0)return;
        if (type.equals("福利")){
            MyWelfareHolder mHolder = (MyWelfareHolder) holder;
            String images = data.get(position).getUrl();
            if (images != null) {
                ViewGroup.LayoutParams layoutParams = mHolder.mItemWelfareImage.getLayoutParams();
                layoutParams.width = width;
                mHolder.mItemWelfareImage.setLayoutParams(layoutParams);
                GlideApp.with(mActivity).load(images) .fallback(new ColorDrawable(Color.GRAY)).into(mHolder.mItemWelfareImage);
                handleSaveImage(mHolder);
            }
        }else {
            MyHolder mHolder = (MyHolder) holder;
            if (data != null && data.size() != 0) {
                mHolder.mItemTitle.setText(data.get(position).getDesc());
                List<String> images = data.get(position).getImages();
                if (images != null) {
                    GlideApp.with(mActivity).load(images.get(0) + "?imageView2/0/w/100").into(mHolder.mItemImage);

                }
            }
        }


    }

    private void handleSaveImage(MyWelfareHolder mHolder) {
        mHolder.mItemWelfareImage.setOnLongClickListener(v -> {
            rxPermissions
                    .requestEachCombined(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe(permission -> {
                        if (permission.granted) {
                            Log.e(TAG,"申请成功");
                            new AlertDialog.Builder(mActivity).setTitle("是否保存图片")
                                    .setPositiveButton("确认", (dialog, which) -> {
                                        BitmapDrawable drawable = (BitmapDrawable) mHolder.mItemWelfareImage.getDrawable();
                                        Bitmap bitmap = drawable.getBitmap();
                                        File externalStorageDirectory = Environment.getExternalStorageDirectory();
                                        File gankFileDir = new File(externalStorageDirectory.getAbsolutePath()+"/gankPic");
                                        if (!gankFileDir.exists()){
                                            gankFileDir.mkdirs();
                                        }
                                        File file = new File(externalStorageDirectory.getAbsolutePath()+"/gankPic/"+System.currentTimeMillis()+".png");
                                        Utils.saveBitmap(bitmap,file);
                                    })
                                    .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                                    .show();


                        } else if (permission.shouldShowRequestPermissionRationale){
                            Log.e("5555","拒绝没点不再询问");
                        }else {//拒绝且不再询问
                            Log.e("5555","拒绝且不再询问");
                            new AlertDialog.Builder(mActivity)
                                    .setTitle("是否前往设置权限")
                                    .setPositiveButton("确认", (dialog, which) -> {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", mActivity.getApplicationContext().getPackageName(), null);
                                        intent.setData(uri);
                                        mActivity.startActivity(intent);
                                    })
                                    .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                                    .show();

                        }
                    });
            return false;
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    class MyHolder extends RecyclerView.ViewHolder {


        ImageView mItemImage;
        TextView mItemTitle;

        public MyHolder(Activity mActivity) {
            super(View.inflate(mActivity, R.layout.item_news, null));
            mItemImage = itemView.findViewById(R.id.m_item_image);
            mItemTitle = itemView.findViewById(R.id.m_item_title);
        }
    }
    static class MyWelfareHolder extends RecyclerView.ViewHolder {


        ImageView mItemWelfareImage;

        public MyWelfareHolder(Activity mActivity) {
            super(View.inflate(mActivity, R.layout.item_news_welfare, null));
            mItemWelfareImage = (ImageView)itemView.findViewById(R.id.m_item_welfare_image);
        }
    }
}
