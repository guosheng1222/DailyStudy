package com.example.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.example.base.BaseData;
import com.example.base.BaseFragment;
import com.example.bean.BeanHotTitle;
import com.example.dailystudy.R;
import com.example.manager.ChangeHideManager;
import com.example.util.UrlUtils;
import com.example.view.JudgeShowView;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by PC on 2017/1/12.
 */

public class CircleHotFragment extends BaseFragment {
    private View view;
    ArrayList<BeanHotTitle.DataBean> titleList=new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager circle_hot_vp;

    @Override
    protected View setSuccessView(int statusCurrent) {
        view = View.inflate(getActivity(),R.layout.circle_hot_fragment,null);
        ChangeHideManager.changeVisible(view, statusCurrent);
        tabLayout = (TabLayout) view.findViewById(R.id.circle_hot_tablayout);
        circle_hot_vp = (ViewPager) view.findViewById(R.id.circle_hot_vp);
        return view;
    }

    //设置顶部标题
    private void initTitle() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                circle_hot_vp.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        circle_hot_vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment f1=CircleHotVpFragment.getFragment(titleList.get(position).getName(),titleList.get(position).getTid());
                return f1;
            }
            @Override
            public int getCount() {
                return titleList.size();
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position).getName();
            }
        });
        tabLayout.setupWithViewPager(circle_hot_vp);
        circle_hot_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    @Override
    protected View setDifferentView(int status) {
        view = View.inflate(getActivity(),R.layout.circle_hot_fragment,null);
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
                BeanHotTitle beanHotTitle = gson.fromJson(data, BeanHotTitle.class);
                titleList= (ArrayList<BeanHotTitle.DataBean>) beanHotTitle.getData();
                initTitle();
            }

            @Override
            public void setResultError() {
                judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_NO_NETWORK);
            }
        }.getData(getActivity(), UrlUtils.circle_hottitle,null,0,BaseData.NOTIME);
    }
}
