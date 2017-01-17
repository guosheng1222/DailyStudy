package com.example.dailystudy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.manager.FragmentFactory;
import com.example.util.CommonUtils;


public class MainActivity extends AppCompatActivity {

    private ViewPager main_viewPager;

    String[] fragmentName = new String[]{"HomePagerFragment", "CategoryFragment", "CircleFragment", "MineFragment"};
    private RadioGroup main_radioGroup;
    private int redColor;
    private int grayColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //设置Fragment和RadioGroup同步
        setScrollSync();
    }
    private void setScrollSync() {

        //获取字体色值
        redColor = CommonUtils.getResources().getColor(R.color.rb_text_check);
        grayColor = CommonUtils.getResources().getColor(R.color.gray);


        main_viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return FragmentFactory.getFragment(fragmentName[position]);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });


        main_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                for (int i = 0; i < main_radioGroup.getChildCount(); i++) {
                    RadioButton childAt = (RadioButton) main_radioGroup.getChildAt(i);
                    if (childAt.getId() == checkId) {
                        main_viewPager.setCurrentItem(i);
                    }
                }
            }
        });
        main_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < main_radioGroup.getChildCount(); i++) {
                    RadioButton childAt = (RadioButton) main_radioGroup.getChildAt(i);
                    if (position == i) {
                        childAt.setTextColor(redColor);
                        childAt.setChecked(true);
                    } else {
                        childAt.setTextColor(grayColor);
                        childAt.setChecked(false);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }

    private void initView() {
        main_viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        main_radioGroup = (RadioGroup) findViewById(R.id.main_radioGroup);
    }
}
