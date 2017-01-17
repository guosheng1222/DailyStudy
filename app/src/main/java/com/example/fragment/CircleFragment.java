package com.example.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.example.base.BaseData;
import com.example.base.BaseFragment;
import com.example.dailystudy.R;
import com.example.manager.ChangeHideManager;
import com.example.manager.FragmentFactory;
import com.example.util.UrlUtils;
import com.example.view.JudgeShowView;


/**
 * Created by lenovo on 2017/1/11.
 */

public class CircleFragment extends BaseFragment {
    String[] title = new String[]{"话题", "热门", "关注"};
    private View view;
    private TabLayout circle_tablayout;
    private ViewPager circle_vp;

    @Override
    protected View setSuccessView(int statusCurrent) {
        view = View.inflate(getActivity(), R.layout.fragment_circle, null);
        ChangeHideManager.changeVisible(view, statusCurrent);
        circle_tablayout = (TabLayout) view.findViewById(R.id.circle_tablayout);
        circle_vp = (ViewPager) view.findViewById(R.id.circle_vp);
        return view;
    }

    protected View setDifferentView(int status) {
        view = View.inflate(getActivity(),R.layout.fragment_circle,null);
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
                initTab();
            }

            @Override
            public void setResultError() {
                judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_NO_NETWORK);
            }
        }.getData(getActivity(), UrlUtils.circle_topic, null, 0, BaseData.NOTIME);
    }

    private void initTab() {
        LinearLayout linearLayout = (LinearLayout) circle_tablayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(25);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                R.drawable.layout_divider_vertical));
        circle_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                circle_vp.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        circle_vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FragmentFactory.getFragment(title[position]);
            }
            @Override
            public int getCount() {
                return 3;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        circle_tablayout.setupWithViewPager(circle_vp);
        circle_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                circle_tablayout.getTabAt(position).select();
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
