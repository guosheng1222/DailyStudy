package com.example.dailystudy;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MyProjectActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView titlename;
    private TextView hide;
    private RadioGroup learn_rg;
    private ViewPager learn_vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);
        //修改标题名
        titlename = (TextView) findViewById(R.id.titlename);
        titlename.setText("我的课程");
        hide = (TextView) findViewById(R.id.hide);
        hide.setVisibility(View.VISIBLE);
        hide.setText("管理");
        //RadioButton ViewPager
        learn_rg = (RadioGroup) findViewById(R.id.learn_rg);
        learn_vp = (ViewPager) findViewById(R.id.learn_vp);
        learn_rg.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < learn_rg.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) learn_rg.getChildAt(i);
            if (radioButton.getId() == checkedId) {
                radioButton.setTextColor(getResources().getColor(R.color.red));
                radioButton.setBackgroundColor(Color.WHITE);
            } else {
                radioButton.setTextColor(Color.BLACK);
                radioButton.setBackgroundColor(getResources().getColor(R.color.radiobutton_bg));
            }
        }
    }
}
