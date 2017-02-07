package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.CourseData;
import com.example.dailystudy.R;

import java.util.List;

/**
 * Created by lenovo on 2017/2/4.
 */

public class CourseAdapter extends BaseAdapter {

    private Context context;
    private List<CourseData.DatalistBean> datalist;
    private final int red;
    private final int green;

    public CourseAdapter(Context context, List<CourseData.DatalistBean> datalist) {
        this.context = context;
        this.datalist = datalist;
        red = context.getResources().getColor(R.color.red);
        green = context.getResources().getColor(R.color.green);
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.course_listitem, null);

            viewHolder.course_list_item_iv_main = (ImageView) convertView.findViewById(R.id.course_list_item_iv_main);
            viewHolder.course_list_item_iv_small = (ImageView) convertView.findViewById(R.id.course_list_item_iv_small);
            viewHolder.course_list_item_tv1 = (TextView) convertView.findViewById(R.id.course_list_item_tv1);
            viewHolder.course_list_item_tv2 = (TextView) convertView.findViewById(R.id.course_list_item_tv2);
            viewHolder.course_list_item_tv3 = (TextView) convertView.findViewById(R.id.course_list_item_tv3);
            viewHolder.course_list_item_tv4 = (TextView) convertView.findViewById(R.id.course_list_item_tv4);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.course_list_item_tv1.setText(datalist.get(position).getCourse_name());
        viewHolder.course_list_item_tv2.setText(datalist.get(position).getSchool_name());
        if (datalist.get(position).getCourse_price().equals("0.00")) {
            viewHolder.course_list_item_tv3.setText("免费");
            viewHolder.course_list_item_tv3.setTextColor(green);
        } else {
            viewHolder.course_list_item_tv3.setText("￥ " + datalist.get(position).getCourse_price());
            viewHolder.course_list_item_tv3.setTextColor(red);
        }
        viewHolder.course_list_item_tv4.setText(datalist.get(position).getCourse_paycount() + "人在线");
        Glide.with(context).load(datalist.get(position).getCourse_pic()).error(R.mipmap.course_error).placeholder(R.mipmap.course_error).into(viewHolder.course_list_item_iv_main);
        return convertView;
    }

    class ViewHolder {
        ImageView course_list_item_iv_main, course_list_item_iv_small;
        TextView course_list_item_tv1, course_list_item_tv2, course_list_item_tv3, course_list_item_tv4;
    }

}
