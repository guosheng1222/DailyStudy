package com.example.fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.adapter.MyPagerAdapter;
import com.example.base.BaseData;
import com.example.base.BaseFragment;
import com.example.bean.HomeContent;
import com.example.dailystudy.R;
import com.example.manager.ChangeHideManager;
import com.example.util.UrlUtils;
import com.example.view.JudgeShowView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2017/1/11.
 */

public class HomePagerFragment extends BaseFragment {

    private View view;
    private ViewPager home_vp;
    public String data;
    private HomeContent homeContent;
    /**
     * 成功视图
     *
     * @param statusCurrent
     * @return
     */
    @Override
    protected View setSuccessView(int statusCurrent) {

        view = View.inflate(getActivity(), R.layout.fragment_homepage, null);
        ChangeHideManager.changeVisible(view, statusCurrent);

        //查找控件
        initview();
        //轮播图
        RoolViewPager();

        return view;
    }

    @Override
    protected View setDifferentView(int status) {
        view = View.inflate(getActivity(), R.layout.fragment_homepage, null);
        ChangeHideManager.changeVisible(view, status);
        return view;
    }

    private void RoolViewPager() {

        List<HomeContent.DataBean.SliderBean> imagelist = homeContent.getData().getSlider();
        home_vp.setAdapter(new MyPagerAdapter(getActivity(), (ArrayList<HomeContent.DataBean.SliderBean>) imagelist));

    }

    private void initview() {
        home_vp = (ViewPager) view.findViewById(R.id.home_vp);
    }

    @Override
    public void onLoad() {
        judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_LOADING);

        new BaseData() {
            @Override
            public void setResultData(String data) {
                HomePagerFragment.this.data = data;
                Gson gson = new Gson();
                homeContent = gson.fromJson(data, HomeContent.class);
                judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_SUCCESS);
            }

            @Override
            protected void setResultError() {
                judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_NO_NETWORK);
            }
        }.getData(getActivity(), UrlUtils.home, null, 0, BaseData.NORMALTIME);

    }


}
