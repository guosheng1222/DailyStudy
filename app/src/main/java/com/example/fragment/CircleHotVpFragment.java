package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.base.BaseData;
import com.example.base.BaseFragment;
import com.example.bean.BeanHotContent;
import com.example.dailystudy.R;
import com.example.manager.ChangeHideManager;
import com.example.util.UrlUtils;
import com.example.view.CircleImageView;
import com.example.view.JudgeShowView;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.dailystudy.R.id.hot_first_iv;
import static com.example.dailystudy.R.id.hot_iv1;
import static com.example.dailystudy.R.id.hot_iv2;
import static com.example.dailystudy.R.id.hot_iv3;
import static com.example.dailystudy.R.id.hot_two_iv1;

/**
 * Created by PC on 2017/1/16.
 */

public class CircleHotVpFragment extends BaseFragment {
    View view;
    HashMap<String,String> argsMap=new HashMap<>();
    ArrayList<BeanHotContent.DataBean> contentList=new ArrayList<>();
    private RecyclerView myRecycler;
    private String title;

    @Override
    protected View setSuccessView(int statusCurrent) {
        view = View.inflate(getActivity(),R.layout.circle_hot_vp_fragment,null);
        ChangeHideManager.changeVisible(view, statusCurrent);
        myRecycler = (RecyclerView) view.findViewById(R.id.circle_hot_vp_recyclerView);
        //把参数放入集合
        String tid = getArguments().getString("tid");
        title = getArguments().getString("title");
        argsMap.put("tid",tid);
        argsMap.put("page","1");
        return view;
    }

    @Override
    protected View setDifferentView(int status) {
        //设置加载失败的视图
        view = View.inflate(getActivity(),R.layout.circle_hot_vp_fragment,null);
        ChangeHideManager.changeVisible(view, status);
        return view;
    }
    //设置列表
    private void initRecycler() {
        //设置布局管理器
        myRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置适配器
        myRecycler.setAdapter(new CommonAdapter<BeanHotContent.DataBean>(getActivity(),R.layout.hot_recycler_item,contentList) {
            @Override
            protected void convert(ViewHolder holder, BeanHotContent.DataBean dataBean, int position) {

                CircleImageView hot_userIcon = holder.getView(R.id.hot_userIcon);
                Glide.with(getActivity())
                        .load(dataBean.getUser_small_log())
                        .placeholder(R.mipmap.head)
                        .error(R.mipmap.head)
                        .into(hot_userIcon);
                holder.setText(R.id.hot_userName,dataBean.getUser_name());
                holder.setText(R.id.hot_pTitle,dataBean.getP_title());
                if(dataBean.getP_leaderette()!=null)
                {
                    holder.setText(R.id.hot_pLeaderette,dataBean.getP_leaderette()+"");
                }else{
                    holder.setText(R.id.hot_pLeaderette,"");
                }
                AutoLinearLayout hot_llt_three = holder.getView(R.id.hot_llt_three);
                holder.setText(R.id.hot_type,"#"+title+"#");
                holder.setText(R.id.hot_likeCount,dataBean.getP_replycount());
                holder.setText(R.id.hot_messageCount,dataBean.getP_collectcount());
                holder.setText(R.id.hot_shareCount,dataBean.getP_sharecount());
                ImageView three_iv1 = holder.getView(hot_iv1);
                ImageView three_iv2 = holder.getView(hot_iv2);
                ImageView three_iv3 = holder.getView(hot_iv3);
                ImageView two_iv1 =holder.getView(hot_two_iv1);
                ImageView two_iv2 =holder.getView(hot_two_iv1);
                ImageView first_iv =holder.getView(hot_first_iv);
                ImageView hot_more_img = holder.getView(R.id.hot_more_img);
                TextView hot_more_tv = holder.getView(R.id.hot_more_tv);
                AutoRelativeLayout hot_more_rl = holder.getView(R.id.hot_more_rl);

                String source = dataBean.getSource();
                if (source!= null) {
                    Gson gson1 = new Gson();
                    String[] strings = gson1.fromJson(source, String[].class);
                    if (strings.length > 0) {
                        hot_llt_three.setVisibility(View.VISIBLE);
                        if (strings.length == 1) {
                            first_iv.setVisibility(View.VISIBLE);
                            Glide.with(getActivity())
                                    .load(strings[0])
                                    .placeholder(R.mipmap.default_one)
                                    .error(R.mipmap.default_one)
                                    .into(first_iv);
                        } else {
                            three_iv1.setVisibility(View.GONE);
                            three_iv2.setVisibility(View.GONE);
                            three_iv3.setVisibility(View.GONE);
                            two_iv1.setVisibility(View.GONE);
                            two_iv2.setVisibility(View.GONE);
                        }
                        if (strings.length == 2) {
                            two_iv1.setVisibility(View.VISIBLE);
                            two_iv2.setVisibility(View.VISIBLE);
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
                        } else {
                            three_iv1.setVisibility(View.GONE);
                            three_iv2.setVisibility(View.GONE);
                            three_iv3.setVisibility(View.GONE);
                            first_iv.setVisibility(View.GONE);
                        }
                        if (strings.length == 3) {
                            hot_more_rl.setVisibility(View.VISIBLE);
                            three_iv1.setVisibility(View.VISIBLE);
                            three_iv2.setVisibility(View.VISIBLE);
                            three_iv3.setVisibility(View.VISIBLE);
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
                        } else {
                            two_iv1.setVisibility(View.GONE);
                            two_iv2.setVisibility(View.GONE);
                            first_iv.setVisibility(View.GONE);
                        }
                        if (strings.length > 3) {
                            three_iv1.setVisibility(View.VISIBLE);
                            three_iv2.setVisibility(View.VISIBLE);
                            three_iv3.setVisibility(View.VISIBLE);
                            hot_more_img.setVisibility(View.VISIBLE);
                            hot_more_tv.setVisibility(View.VISIBLE);
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
                        } else {
                            two_iv1.setVisibility(View.GONE);
                            two_iv2.setVisibility(View.GONE);
                            first_iv.setVisibility(View.GONE);
                        }
                    } else {
                        hot_llt_three.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
    @Override
    public void onLoad() {
        judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_LOADING);
        new BaseData() {
            @Override
            public void setResultData(String data) {
                judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_SUCCESS);
                Gson gson=new Gson();
                BeanHotContent beanHotContent = gson.fromJson(data, BeanHotContent.class);
                if(beanHotContent.getData()!=null)
                {
                    contentList= (ArrayList<BeanHotContent.DataBean>) beanHotContent.getData();
                    initRecycler();
                }
                else
                {
                    onLoad();
                }
            }
            @Override
            public void setResultError() {
                judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_NO_NETWORK);
            }
        }.postData(getActivity(), UrlUtils.circle_hot_content,argsMap,0,BaseData.NOTIME);
    }

    public static Fragment getFragment(String title,String tid)
    {
        Fragment f1=new CircleHotVpFragment();
        Bundle bundle=new Bundle();
        bundle.putString("tid",tid);
        bundle.putString("title",title);
        f1.setArguments(bundle);
        return f1;
    }
}
