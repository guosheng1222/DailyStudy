package com.example.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.base.BaseData;
import com.example.base.BaseFragment;
import com.example.bean.BeanHotContent;
import com.example.dailystudy.R;
import com.example.manager.ChangeHideManager;
import com.example.util.UrlUtils;
import com.example.view.CircleImageView;
import com.example.view.JudgeShowView;
import com.example.view.MyHeader;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.melnykov.fab.FloatingActionButton;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.dailystudy.R.id.hot_first_iv;
import static com.example.dailystudy.R.id.hot_iv1;
import static com.example.dailystudy.R.id.hot_iv2;
import static com.example.dailystudy.R.id.hot_iv3;
import static com.example.dailystudy.R.id.hot_two_iv1;
import static com.example.dailystudy.R.id.hot_two_iv2;

/**
 * Created by PC on 2017/2/6.
 */

public class TopicFragment extends BaseFragment implements  SpringView.OnFreshListener {
    private View view;
    HashMap<String, String> argsMap = new HashMap<>();
    private RecyclerView myRecycler;
    ArrayList<BeanHotContent.DataBean> contentList=new ArrayList<>();
    private SpringView mySpringview;
    @Override
    protected View setSuccessView(int statusCurrent) {
        view = View.inflate(getActivity(), R.layout.topic_fragment, null);
        ChangeHideManager.changeVisible(view, statusCurrent);
        myRecycler = (RecyclerView) view.findViewById(R.id.circle_hot_vp_recyclerView);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.attachToRecyclerView(myRecycler);

        mySpringview = (SpringView)view.findViewById(R.id.mySpringView);
        mySpringview.setHeader(new MyHeader());
        mySpringview.setFooter(new DefaultFooter(getActivity()));
        mySpringview.setType(SpringView.Type.FOLLOW);
        mySpringview.setListener(this);
        return view;
    }

    @Override
    protected View setDifferentView(int status) {
        //设置加载失败的视图
        view = View.inflate(getActivity(), R.layout.topic_fragment, null);
        ChangeHideManager.changeVisible(view, status);
        LinearLayout no_network = (LinearLayout) view.findViewById(R.id.no_network);
        no_network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoad();
            }
        });
        return view;
    }
    @Override
    public void onLoad() {
        String nid = getArguments().getString("nid");
        String order = getArguments().getString("order");

        argsMap.put("nid", nid);
        argsMap.put("order", order);
        argsMap.put("page", "1");
        judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_LOADING);
        new BaseData() {
           @Override
           public void setResultData(String data) {
               judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_SUCCESS);
               Gson gson = new Gson();
               BeanHotContent beanHotContent = gson.fromJson(data, BeanHotContent.class);
               if (beanHotContent.getData() != null) {
                   contentList = (ArrayList<BeanHotContent.DataBean>) beanHotContent.getData();
                   initRecycler();
               } else {
                   onLoad();
               }
           }
            @Override
           public void setResultError() {
               judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_NO_NETWORK);
           }
       }.postData(getActivity(),UrlUtils.circle_topic_bottom,argsMap,0,BaseData.NOTIME);
    }

    private void initRecycler() {
        //设置布局管理器
        myRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycler.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
      /*  myRecycler.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });*/
        //设置适配器
        myRecycler.setAdapter(new CommonAdapter<BeanHotContent.DataBean>(getActivity(), R.layout.hot_recycler_item, contentList) {
            @Override
            protected void convert(ViewHolder holder, BeanHotContent.DataBean dataBean, int position) {

                CircleImageView hot_userIcon = holder.getView(R.id.hot_userIcon);
                Glide.with(getActivity())
                        .load(dataBean.getUser_small_log())
                        .placeholder(R.mipmap.head)
                        .error(R.mipmap.head)
                        .into(hot_userIcon);
                holder.setText(R.id.hot_userName, dataBean.getUser_name());
                holder.setText(R.id.hot_pTitle, dataBean.getP_title());
                if (dataBean.getP_leaderette() != null) {
                    holder.setText(R.id.hot_pLeaderette, dataBean.getP_leaderette() + "");
                } else {
                    holder.setText(R.id.hot_pLeaderette, "");
                }
                AutoLinearLayout hot_llt_three = holder.getView(R.id.hot_llt_three);
                AutoLinearLayout hot_llt_two = holder.getView(R.id.hot_llt_two);
                holder.setText(R.id.hot_likeCount, dataBean.getP_dig());
                holder.setText(R.id.hot_messageCount, dataBean.getP_replycount());
                holder.setText(R.id.hot_shareCount, dataBean.getP_sharecount());
                ImageView three_iv1 = holder.getView(hot_iv1);
                ImageView three_iv2 = holder.getView(hot_iv2);
                ImageView three_iv3 = holder.getView(hot_iv3);
                ImageView two_iv1 = holder.getView(hot_two_iv1);
                ImageView two_iv2 = holder.getView(hot_two_iv2);
                ImageView first_iv = holder.getView(hot_first_iv);


                String source = dataBean.getSource();
                if (source != null) {
                    Gson gson1 = new Gson();
                    String[] strings = gson1.fromJson(source, String[].class);
                    if (strings.length > 0) {
                        if (strings.length == 1) {
                            first_iv.setVisibility(View.VISIBLE);
                            hot_llt_two.setVisibility(View.GONE);
                            hot_llt_three.setVisibility(View.GONE);
                            Glide.with(getActivity())
                                    .load(strings[0])
                                    .placeholder(R.mipmap.default_one)
                                    .error(R.mipmap.default_one)
                                    .into(first_iv);
                        } else if (strings.length == 2) {
                            hot_llt_two.setVisibility(View.VISIBLE);
                            first_iv.setVisibility(View.GONE);
                            hot_llt_three.setVisibility(View.GONE);
                            Glide.with(getActivity())
                                    .load(strings[0])
                                    .placeholder(R.mipmap.default_two)
                                    .error(R.mipmap.default_two)
                                    .into(two_iv1);
                            Glide.with(getActivity())
                                    .load(strings[1])
                                    .placeholder(R.mipmap.default_two)
                                    .error(R.mipmap.default_two)
                                    .into(two_iv2);
                        } else if (strings.length == 3) {
                            hot_llt_three.setVisibility(View.VISIBLE);
                            hot_llt_two.setVisibility(View.GONE);
                            first_iv.setVisibility(View.GONE);
                            Glide.with(getActivity())
                                    .load(strings[0])
                                    .placeholder(R.mipmap.default_three)
                                    .error(R.mipmap.default_three)
                                    .into(three_iv1);
                            Glide.with(getActivity())
                                    .load(strings[1])
                                    .placeholder(R.mipmap.default_three)
                                    .error(R.mipmap.default_three)
                                    .into(three_iv2);
                            Glide.with(getActivity())
                                    .load(strings[2])
                                    .placeholder(R.mipmap.default_three)
                                    .error(R.mipmap.default_three)
                                    .into(three_iv3);
                        } else if (strings.length > 3) {
                            hot_llt_three.setVisibility(View.VISIBLE);
                            hot_llt_two.setVisibility(View.GONE);
                            first_iv.setVisibility(View.GONE);
                            Glide.with(getActivity())
                                    .load(strings[0])
                                    .placeholder(R.mipmap.default_three)
                                    .error(R.mipmap.default_three)
                                    .into(three_iv1);
                            Glide.with(getActivity())
                                    .load(strings[1])
                                    .placeholder(R.mipmap.default_three)
                                    .error(R.mipmap.default_three)
                                    .into(three_iv2);
                            Glide.with(getActivity())
                                    .load(strings[2])
                                    .placeholder(R.mipmap.default_three)
                                    .error(R.mipmap.default_three)
                                    .into(three_iv3);
                        }
                    }
                }
            }
        });
    }

    public static Fragment getFragment(String order, String nid) {
        Fragment f1 = new TopicFragment();
        Bundle bundle = new Bundle();
        bundle.putString("nid", nid);
        bundle.putString("order", order);
        f1.setArguments(bundle);
        return f1;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mySpringview.onFinishFreshAndLoad();
                onLoad();
            }
        }, 1500);
    }

    @Override
    public void onLoadmore() {

    }
}
