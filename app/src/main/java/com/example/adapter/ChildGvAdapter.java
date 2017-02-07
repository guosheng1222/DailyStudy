package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bean.CategoryFace;
import com.example.dailystudy.CateDetailedActivity;
import com.example.dailystudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/1/16.
 */

public class ChildGvAdapter extends BaseAdapter {

    private int index;
    private Context context;
    private List<CategoryFace.NodesBean> nodes;
    private ArrayList<CategoryFace> cateList;

    public ChildGvAdapter(Context context, ArrayList<CategoryFace> cateList, int index) {
        this.context = context;
        this.cateList = cateList;
        this.index = index;
        for (int i = 0; i < cateList.size(); i++) {
            if (i == index) {
                nodes = cateList.get(index).getNodes();
            }
        }
    }

    @Override
    public int getCount() {
        return nodes.size() + 1;
    }

    @Override
    public Object getItem(int i) {
        if (i == 0) {
            return "全部";
        }
        return nodes.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        view = View.inflate(context, R.layout.cate_childrv_item, null);

        TextView title = (TextView) view.findViewById(R.id.cate_childrv_title);
        if (position == 0) {
            title.setText("全部");
        } else {
            title.setText(nodes.get(position - 1).getCategory_name());
        }

        //条目点击事件
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CateDetailedActivity.class);

                if (position != 0) {
                    intent.putExtra("id", nodes.get(position - 1).getId());
                }
                intent.putExtra("name", cateList.get(index).getCname());
                context.startActivity(intent);
            }
        });

        return view;
    }

}
