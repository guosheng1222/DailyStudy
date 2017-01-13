package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bean.HomeContent;
import com.example.dailystudy.R;
import com.example.util.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/1/12.
 */
public class MyPagerAdapter extends PagerAdapter {

    private FragmentActivity activity;
    private ArrayList<HomeContent.DataBean.SliderBean> list;
    public MyPagerAdapter(FragmentActivity activity, ArrayList<HomeContent.DataBean.SliderBean> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(activity, R.layout.rool_image, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.roolimage);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        GlideUtils.loadImageView(activity,list.get(position).getImg(),imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
