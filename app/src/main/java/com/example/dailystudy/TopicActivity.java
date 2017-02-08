package com.example.dailystudy;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.base.BaseData;
import com.example.bean.BeanTopicTitle;
import com.example.fragment.TopicFragment;
import com.example.util.GlideRoundTransform;
import com.example.util.UrlUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class TopicActivity extends AppCompatActivity {
    HashMap<String, String> argsMap = new HashMap<>();
    private TextView tv_title;
    private ImageView iv_background;
    private ImageView iv_user;
    private TextView tv_toptitle;
    private TextView tv_topBrief;
    private TextView tv_userCount;
    private TextView tv_topPostCount;
    private String[] titleArgs=new String[]{"最新","最热"};
    private TabLayout tablayout;
    private ViewPager topViewPager;
    private String nid;
    private ImageView back;
    public  AppBarLayout appBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBar);
        tv_title = (TextView) findViewById(R.id.topic_tv_title);
        iv_background = (ImageView) findViewById(R.id.topback_iv);
        iv_user = (ImageView) findViewById(R.id.topUser_iv);
        tv_toptitle = (TextView) findViewById(R.id.topTitle);
        tv_topBrief = (TextView) findViewById(R.id.topBrief);
        tv_userCount = (TextView) findViewById(R.id.userCount);
        tv_topPostCount = (TextView) findViewById(R.id.topPostCount);
        tablayout = (TabLayout) findViewById(R.id.topTableLayout);
        topViewPager = (ViewPager) findViewById(R.id.topViewPager);
        back = (ImageView) findViewById(R.id.back);
        nid = getIntent().getStringExtra("nid");
        String title = getIntent().getStringExtra("title");
        tv_title.setText(title);
        argsMap.put("nid", nid);
        argsMap.put("order","1");
        argsMap.put("page","1");

        onload();
        initTitle();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initTitle() {
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                topViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        topViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return titleArgs.length;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment f1;
                if(position==0){
                    f1 = TopicFragment.getFragment("1",nid);
                }else{
                    f1 = TopicFragment.getFragment("2",nid);
                }

                return f1;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleArgs[position];
            }
        });
        tablayout.setupWithViewPager(topViewPager);
        topViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                tablayout.getTabAt(position).select();
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void onload(){
        new BaseData() {
            @Override
            public void setResultData(String data) {
                Gson gson=new Gson();
                BeanTopicTitle beanTopicTitle = gson.fromJson(data, BeanTopicTitle.class);
                Glide.with(TopicActivity.this).load(beanTopicTitle.getData().getN_big_img()).into(iv_background);
                Glide.with(TopicActivity.this).load(beanTopicTitle.getData().getN_small_img()).transform(new GlideRoundTransform(TopicActivity.this,5)).into(iv_user);
                tv_toptitle.setText(beanTopicTitle.getData().getN_title());
                tv_topBrief.setText(beanTopicTitle.getData().getN_brief());
                tv_userCount.setText(beanTopicTitle.getData().getN_user_count());
                tv_topPostCount.setText(beanTopicTitle.getData().getN_post_count());
            }
            @Override
            public void setResultError() {

            }
        }.postData(TopicActivity.this, UrlUtils.circle_topic_title,argsMap,0,BaseData.NOTIME);
    }
}
