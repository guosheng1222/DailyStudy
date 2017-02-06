package com.example.fragment;

import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.base.BaseData;
import com.example.base.BaseFragment;
import com.example.bean.BeanTopic;
import com.example.dailystudy.R;
import com.example.manager.ChangeHideManager;
import com.example.util.CommonUtils;
import com.example.util.GlideImageLoader;
import com.example.util.GlideUtils;
import com.example.util.UrlUtils;
import com.example.view.JudgeShowView;
import com.example.view.MyHeader;
import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.zhy.adapter.recyclerview.CommonAdapter;
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

    @Override
    protected View setSuccessView(int statusCurrent) {
        view = CommonUtils.inflate(R.layout.circle_topic_fragment);
        ChangeHideManager.changeVisible(view, statusCurrent);
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
    //
    private void intiRecycler() {
        hotcircle.setLayoutManager(new LinearLayoutManager(getActivity()));
        hotcircle.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        hotcircle.setAdapter(new CommonAdapter<BeanTopic.DataBean.CircleBean>(getActivity(),R.layout.topic_recycleritem_layout,circleList) {
            @Override
            protected void convert(ViewHolder holder, BeanTopic.DataBean.CircleBean circleBean, int position) {
                holder.setText(R.id.topic_Title,circleList.get(position).getN_title());
                holder.setText(R.id.topic_Brief,circleList.get(position).getN_brief());
                holder.setText(R.id.topic_userCount,circleList.get(position).getN_user_count()+"关注");
                holder.setText(R.id.topic_postCount,circleList.get(position).getN_post_count()+"帖子");
                ImageView image = holder.getView(R.id.topic_iv);
                GlideUtils.loadImageView(getActivity(),circleList.get(position).getN_small_img(),image);
            }
        });
    }
    @Override
    protected View setDifferentView(int status) {
        view = View.inflate(getActivity(),R.layout.circle_topic_fragment,null);
        ChangeHideManager.changeVisible(view, status);
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