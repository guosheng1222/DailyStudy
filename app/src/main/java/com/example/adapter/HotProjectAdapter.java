package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.bean.HomeContent;
import com.example.dailystudy.R;
import com.example.util.GlideUtils;

import java.util.List;

/**
 * Created by ASUS on 2017/1/14.
 */
public class HotProjectAdapter extends BaseAdapter {

    private Context context;
    private List<HomeContent.DataBean.HotcourseBean> list;

    public HotProjectAdapter(Context context, List<HomeContent.DataBean.HotcourseBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.hotcourse, null);
        ImageView course_img = (ImageView) view.findViewById(R.id.course_img);
        TextView course_dec = (TextView) view.findViewById(R.id.course_dec);
        TextView course_name = (TextView) view.findViewById(R.id.course_name);
        GlideUtils.loadImageView(context, list.get(position).getImg(), course_img);
        course_dec.setText(list.get(position).getTitle());
        course_name.setText(list.get(position).getName());
        return view;
    }
}
