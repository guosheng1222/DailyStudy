package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.ThreeList;
import com.example.dailystudy.R;
import com.example.util.CommonUtils;

import java.util.List;

/**
 * Created by lenovo on 2017/1/17.
 */

public class ThreeBaseAdapter extends BaseAdapter {

    private final int childIndex;
    private Context context;
    private ThreeList[] threeLists;
    private int sign;
    private List<ThreeList.NodesBean> nodes;
    private List<ThreeList.NodesBean.Nodes2Bean> nodes2;
    private int node_index;
    private int node2_index;
    private final int red;

    public ThreeBaseAdapter(Context context, ThreeList[] threeLists, int sign, int node_index,
                            int node2_index, int childIndex) {
        this.context = context;
        this.threeLists = threeLists;
        this.sign = sign;
        this.node_index = node_index;
        this.node2_index = node2_index;
        this.childIndex = childIndex;
        red = context.getResources().getColor(R.color.red);

    }

    @Override
    public int getCount() {
        if (sign == 1) {
            return threeLists.length;
        } else if (sign == 2) {
            nodes = threeLists[node_index].getNodes();
            return nodes.size();
        }
        nodes2 = threeLists[node_index].getNodes().get(node2_index).getNodes2();
        return nodes2.size();
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

        if (sign == 1) {

            int[] logo = new int[]{R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
                    R.drawable.five, R.drawable.six};
            convertView = CommonUtils.inflate(R.layout.list_first_item);
            ImageView iv_list_first_item = (ImageView) convertView.findViewById(R.id.iv_list_first_item);
            TextView tv_list_first_item = (TextView) convertView.findViewById(R.id.tv_list_first_item);

            iv_list_first_item.setImageResource(logo[position]);
            tv_list_first_item.setText(threeLists[position].getMenu().getCategory_name());
            if (position == node_index) {
                convertView.setBackgroundColor(context.getResources().getColor(R.color.bg_list2));
                tv_list_first_item.setTextColor(red);
            }
        } else if (sign == 2) {

            convertView = CommonUtils.inflate(R.layout.simple_list_item);
            TextView simple_list_tv = (TextView) convertView.findViewById(R.id.simple_list_tv);
            simple_list_tv.setText(nodes.get(position).getMenu2().getCategory_name());
            if (position == node2_index) {
                convertView.setBackgroundColor(context.getResources().getColor(R.color.bg_list3));
                simple_list_tv.setTextColor(red);
            }
        } else {
            convertView = CommonUtils.inflate(R.layout.simple_list_item);
            TextView simple_list_tv = (TextView) convertView.findViewById(R.id.simple_list_tv);
            simple_list_tv.setText(nodes2.get(position).getMenu3().getCategory_name());
            if (position == childIndex) {
                simple_list_tv.setTextColor(red);
            }
        }


        return convertView;
    }
}
