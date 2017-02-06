package com.example.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.ChildGvAdapter;
import com.example.base.BaseData;
import com.example.base.BaseFragment;
import com.example.bean.CategoryFace;
import com.example.dailystudy.R;
import com.example.manager.ChangeHideManager;
import com.example.util.CommonUtils;
import com.example.util.UrlUtils;
import com.example.view.JudgeShowView;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2017/1/11.
 */

public class CategoryFragment extends BaseFragment {

    private View view;
    private RecyclerView cate_rv;
    private CategoryFace[] categoryFace;
    private ArrayList<CategoryFace> cateList;
    private int[] title_color;
    private int[] leftIv;
    private int[] rightIv;
    int clickPosition = -1;


    /**
     * 请求数据成功的视图
     *
     * @param statusCurrent
     * @return
     */
    @Override
    protected View setSuccessView(int statusCurrent) {
        view = CommonUtils.inflate(R.layout.fragment_category);

        ChangeHideManager.changeVisible(view, statusCurrent);
        //查找控件
        initView();
        //RecyclerView适配
        initRecyclerView();

        return view;
    }

    /**
     * 设置RecyclerView适配
     */
    private void initRecyclerView() {


        leftIv = new int[]{R.drawable.heart, R.drawable.coffee, R.drawable.diamond,
                R.drawable.fourr, R.drawable.hat, R.drawable.language};

        int color1 = getResources().getColor(R.color.cate_title1);
        int color2 = getResources().getColor(R.color.cate_title2);
        int color3 = getResources().getColor(R.color.cate_title3);
        int color4 = getResources().getColor(R.color.cate_title4);
        int color5 = getResources().getColor(R.color.cate_title5);
        int color6 = getResources().getColor(R.color.cate_title6);

        title_color = new int[]{color1, color2, color3, color4, color5, color6};
        rightIv = new int[]{R.drawable.up1, R.drawable.up2, R.drawable.up3,
                R.drawable.up1, R.drawable.up5, R.drawable.up6};
        cateList = new ArrayList<>();
        for (int i = 0; i < categoryFace.length; i++) {
            cateList.add(categoryFace[i]);
        }
        cate_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        cate_rv.setAdapter(new CommonAdapter<CategoryFace>(getActivity(), R.layout.cate_rv_item, cateList) {

            @Override
            protected void convert(ViewHolder holder, CategoryFace categoryFace, final int position) {
                holder.setText(R.id.cate_rv_title, cateList.get(position).getCname());
                holder.setTextColor(R.id.cate_rv_title, R.color.text_normal_color);
                holder.setImageResource(R.id.cate_rv_iv, leftIv[position]);
                holder.setImageResource(R.id.cate_rv_arrow, R.drawable.down);
                holder.setVisible(R.id.cate_rv_childGv, false);


                /**
                 * 点击listview条目
                 */
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View view) {

                        TextView rv_title = (TextView) view.findViewById(R.id.cate_rv_title);
                        ImageView rv_arrow = (ImageView) view.findViewById(R.id.cate_rv_arrow);
                        GridView child_rv = (GridView) view.findViewById(R.id.cate_rv_childGv);
                        rv_arrow.setImageResource(rightIv[position]);
                        rv_title.setTextColor(title_color[position]);

                        if (position != clickPosition) {
                            child_rv.setAdapter(new ChildGvAdapter(getActivity(), cateList, position));
                            notifyItemChanged(clickPosition);
                            child_rv.setVisibility(View.VISIBLE);
                            clickPosition = position;
                        } else {
                            clickPosition = -1;
                            notifyItemChanged(position);
                        }

                    }
                });
            }
        });
    }

    /**
     * 找控件
     */
    private void initView() {
        cate_rv = (RecyclerView) view.findViewById(R.id.cate_rv);
    }

    @Override
    protected View setDifferentView(int status) {
        view = CommonUtils.inflate(R.layout.fragment_category);

        ChangeHideManager.changeVisible(view, status);

        return view;
    }

    @Override
    public void onLoad() {

        new BaseData() {
            @Override
            public void setResultData(String data) {
                CategoryFace[] categoryFace = new Gson().fromJson(data, CategoryFace[].class);
                CategoryFragment.this.categoryFace = categoryFace;
                judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_SUCCESS);
            }

            @Override
            protected void setResultError() {
                judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_NO_NETWORK);
            }
        }.getData(getActivity(), UrlUtils.sort, null, 1, BaseData.NORMALTIME);
    }
}
