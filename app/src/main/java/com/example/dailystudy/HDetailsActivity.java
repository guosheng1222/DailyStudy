package com.example.dailystudy;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.BaseData;
import com.example.bean.Course;
import com.example.fragment.DetailsFragment;
import com.example.util.GlideUtils;
import com.example.util.UrlUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class HDetailsActivity extends AppCompatActivity {

    private TextView pro_name, pro_price;
    private ImageView pro_image;
    private HashMap<String, String> map;
    private Course course;
    private TextView class_num, class_fen, class_usercount;
    private ArrayList<String> tab_list = new ArrayList();
    private TabLayout tabLayout;
    private ViewPager hviewpager;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hdetails);


        url = getIntent().getStringExtra("url");
        map = new HashMap<>();
        map.put("courseid", url);

        hviewpager = (ViewPager) findViewById(R.id.hviewpager);
        pro_name = (TextView) findViewById(R.id.pro_name);
        pro_price = (TextView) findViewById(R.id.pro_price);
        pro_image = (ImageView) findViewById(R.id.pro_image);
        class_num = (TextView) findViewById(R.id.class_num);
        class_fen = (TextView) findViewById(R.id.class_fen);
        class_usercount = (TextView) findViewById(R.id.class_usercount);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tab_list.add("详情");
        tab_list.add("目录");
        tab_list.add("评论");

        tabLayout.addTab(tabLayout.newTab().setText(tab_list.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tab_list.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tab_list.get(2)));
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(15);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.layout_horzaltol_divider_vertical));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int positon = tab.getPosition();
                hviewpager.setCurrentItem(positon);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        Hviewpager.setCurrentItem(1);
        Onload();
    }

    private void Onload() {
        new BaseData() {
            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                course = gson.fromJson(data, Course.class);
                GlideUtils.loadImageView(HDetailsActivity.this, course.getData().getCourse_pic(), pro_image);
                pro_name.setText(course.getData().getCourse_name());
                pro_price.setText("¥" + course.getData().getCourse_price());
                if (pro_price.getText().equals("¥0.00")) {
                    pro_price.setText("免费");
                } else if (!pro_price.getText().equals("0.00")) {
                    pro_price.setText("¥" + course.getData().getCourse_price());
                }
                class_num.setText("总课时：" + course.getData().getCourse_hour());
                class_fen.setText("评分：" + course.getData().getGoodrate());
                class_usercount.setText(course.getData().getCourse_paycount() + "人学");
                hviewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                    @Override
                    public Fragment getItem(int position) {
                        return DetailsFragment.getFragment(tab_list.get(position), course, url);
                    }

                    @Override
                    public int getCount() {
                        return tab_list.size();
                    }
                });
                hviewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


            }

            @Override
            public void setResultError() {


            }
        }.postData(this, UrlUtils.kechengurl, map, 0, BaseData.NOTIME);
    }
}
