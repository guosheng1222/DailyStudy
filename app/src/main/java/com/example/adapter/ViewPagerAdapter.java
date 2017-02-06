package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.bean.RoolDetail;
import com.example.dailystudy.R;
import com.example.util.GlideUtils;

import java.util.List;

import static com.example.util.CommonUtils.getResources;

/**
 * Created by ASUS on 2017/1/17.
 */
public class ViewPagerAdapter extends BaseAdapter {

    private Context context;
    private List<RoolDetail.DataListBean.ListBean> list;

    public ViewPagerAdapter(Context context, List<RoolDetail.DataListBean.ListBean> list) {
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

        View view = View.inflate(context, R.layout.recommend, null);
        ImageView project_img = (ImageView) view.findViewById(R.id.project_img);
        TextView project_name = (TextView) view.findViewById(R.id.project_name);
        TextView school_name = (TextView) view.findViewById(R.id.school_name);
        TextView project_price = (TextView) view.findViewById(R.id.project_price);
        TextView user_num = (TextView) view.findViewById(R.id.user_num);
        GlideUtils.loadImageView(context, list.get(position).getCourse_pic(), project_img);
        project_name.setText(list.get(position).getCourse_name());
        school_name.setText(list.get(position).getSchool_name());
        project_price.setText(list.get(position).getCourse_price() + "");
        if (project_price.getText().equals("0.00")) {
            project_price.setText("免费");
        } else if (!project_price.getText().equals("0.00")) {
            project_price.setTextColor(getResources().getColor(R.color.red));
            project_price.setText("¥" + list.get(position).getCourse_price());
        }
        user_num.setText(list.get(position).getCourse_paycount() + "人正在学习");

        return view;
    }
}
