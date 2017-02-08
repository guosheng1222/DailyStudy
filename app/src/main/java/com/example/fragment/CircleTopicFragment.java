package com.example.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app.MyApplication;
import com.example.base.BaseData;
import com.example.base.BaseFragment;
import com.example.bean.BeanTopic;
import com.example.dailystudy.LandActivity;
import com.example.dailystudy.R;
import com.example.dailystudy.TopicActivity;
import com.example.manager.ChangeHideManager;
import com.example.util.CommonUtils;
import com.example.util.GlideImageLoader;
import com.example.util.GlideRoundTransform;
import com.example.util.UrlUtils;
import com.example.view.JudgeShowView;
import com.example.view.MyHeader;
import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

/**
 * Created by PC on 2017/1/12.
 */

public class CircleTopicFragment extends BaseFragment {

    private View view;
    private Banner banner;
    private BeanTopic beanTopic;
    ArrayList<String> imageList=new ArrayList<>();
    ArrayList<BeanTopic.DataBean.CircleBean> circleList=new ArrayList<>();
    private RecyclerView hotcircle;
    private RecyclerView mycircle;
    private SpringView mySpringview;
    private ArrayList<BeanTopic.DataBean.CircleBean> myCircleList=new ArrayList<>();
    private ImageView addMyTopic;
    private CommonAdapter<BeanTopic.DataBean.CircleBean> myCircleAdapter;
    private LinearLayout lin_mycircle;
    private CommonAdapter<BeanTopic.DataBean.CircleBean> commonAdapter;
    private TextView tv_mycircle;
    private CommonAdapter<BeanTopic.DataBean.CircleBean> mineCommonAdapter;
    @Override
    protected View setSuccessView(int statusCurrent) {
        view = CommonUtils.inflate(R.layout.circle_topic_fragment);
        ChangeHideManager.changeVisible(view, statusCurrent);
        tv_mycircle = (TextView) view.findViewById(R.id.tv_mycircle);
        lin_mycircle = (LinearLayout) view.findViewById(R.id.mycircle);
        banner = (Banner) view.findViewById(R.id.circle_topic_banner);
        hotcircle = (RecyclerView) view.findViewById(R.id.topic_hotcircle_recycler);
        mycircle = (RecyclerView) view.findViewById(R.id.topic_mycircle_recycler);
        mySpringview = (SpringView) view.findViewById(R.id.content);
        mySpringview.setHeader(new MyHeader());
        mySpringview.setType(SpringView.Type.FOLLOW);
        mySpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mySpringview.onFinishFreshAndLoad();
                    }
                }, 1500);
            }
            @Override
            public void onLoadmore() {
            }
        });
        return view;
    }
    private void initBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageList);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
    private void intiRecycler() {
        hotcircle.setLayoutManager(new LinearLayoutManager(getActivity()));
        hotcircle.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        hotcircle.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        commonAdapter = new CommonAdapter<BeanTopic.DataBean.CircleBean>(getActivity(), R.layout.topic_recycleritem_layout, circleList) {
            @Override
            protected void convert(final ViewHolder holder, BeanTopic.DataBean.CircleBean circleBean, final int position) {
                addMyTopic = holder.getView(R.id.addMyTopic);
                holder.setText(R.id.topic_Title, circleList.get(position).getN_title());
                holder.setText(R.id.topic_Brief, circleList.get(position).getN_brief());
                holder.setText(R.id.topic_userCount, circleList.get(position).getN_user_count() + "关注");
                holder.setText(R.id.topic_postCount, circleList.get(position).getN_post_count() + "帖子");
                ImageView image = holder.getView(R.id.topic_iv);
                Glide.with(getActivity()).load(circleList.get(position).getN_small_img()).transform(new GlideRoundTransform(getActivity(),5)).into(image);
                addMyTopic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(MyApplication.isUser){
                            //int adapterPosition = holder.getAdapterPosition();
                            myCircleList.add(circleList.get(position));
                            circleList.remove(position);
                            commonAdapter.notifyDataSetChanged();
                            if (myCircleList.size() > 0) {
                                tv_mycircle.setVisibility(View.VISIBLE);
                                mycircle.setVisibility(View.VISIBLE);
                                lin_mycircle.setVisibility(View.VISIBLE);
                                setMineRecyclerViewAdapater();
                            }
                        }
                        else{
                            Intent intent=new Intent(getActivity(),LandActivity.class);
                            startActivity(intent);
                        }
                    }
                    private void setMineRecyclerViewAdapater() {
                        mycircle.setLayoutManager(new LinearLayoutManager(getActivity()));
                        mycircle.addItemDecoration(new DividerItemDecoration(
                                getActivity(), DividerItemDecoration.VERTICAL));
                        mycircle.setLayoutManager(new LinearLayoutManager(getActivity()) {
                            @Override
                            public boolean canScrollVertically() {
                                return false;
                            }
                        });
                        if (mineCommonAdapter == null) {
                            mineCommonAdapter = new CommonAdapter<BeanTopic.DataBean.CircleBean>(getActivity(), R.layout.topic_recycleritem_layout, myCircleList) {
                                @Override
                                protected void convert(ViewHolder holder, BeanTopic.DataBean.CircleBean circleBean, int position) {
                                    addMyTopic = holder.getView(R.id.addMyTopic);
                                    holder.setText(R.id.topic_Title, myCircleList.get(position).getN_title());
                                    holder.setText(R.id.topic_Brief, myCircleList.get(position).getN_brief());
                                    holder.setText(R.id.topic_userCount, myCircleList.get(position).getN_user_count() + "关注");
                                    holder.setText(R.id.topic_postCount, myCircleList.get(position).getN_post_count() + "帖子");
                                    ImageView image = holder.getView(R.id.topic_iv);
                                    Glide.with(getActivity()).load(myCircleList.get(position).getN_small_img()).transform(new GlideRoundTransform(getActivity(),5)).into(image);
                                }
                            };
                            mycircle.setAdapter(mineCommonAdapter);
                        } else {
                            mineCommonAdapter.notifyDataSetChanged();
                        }
                        mineCommonAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                Intent in=new Intent(getActivity(), TopicActivity.class);
                                in.putExtra("nid",myCircleList.get(position).getNid());
                                in.putExtra("title",myCircleList.get(position).getN_title());
                                startActivity(in);
                            }
                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                                return false;
                            }
                        });
                    }
                });

            }
        };
        hotcircle.setAdapter(commonAdapter);

        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent in=new Intent(getActivity(), TopicActivity.class);
                in.putExtra("nid",circleList.get(position).getNid());
                in.putExtra("title",circleList.get(position).getN_title());
                startActivity(in);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }
    @Override
    protected View setDifferentView(int status) {
        view = View.inflate(getActivity(),R.layout.circle_topic_fragment,null);
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
        judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_LOADING);
        new BaseData() {
            @Override
            public void setResultData(String data) {
                judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_SUCCESS);
                Gson gson=new Gson();
                beanTopic = gson.fromJson(data, BeanTopic.class);
                imageList.clear();
                for (int i = 0; i < beanTopic.getData().getBanner().size(); i++) {
                    imageList.add(beanTopic.getData().getBanner().get(i).getImg());
                }
                circleList= (ArrayList<BeanTopic.DataBean.CircleBean>) beanTopic.getData().getCircle();
                initBanner();
                intiRecycler();
            }

            @Override
            public void setResultError() {
                judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_NO_NETWORK);
            }
        }.getData(getActivity(), UrlUtils.circle_topic, null, 0, BaseData.NOTIME);
    }
}