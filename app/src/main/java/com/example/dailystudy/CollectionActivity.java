package com.example.dailystudy;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CollectionActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView titlename;
    private TextView hide;
    private RadioGroup collection_rg;
    private ViewPager collection_vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        //修改标题名
        titlename = (TextView) findViewById(R.id.titlename);
        titlename.setText("收藏");
        hide = (TextView) findViewById(R.id.hide);
        hide.setVisibility(View.VISIBLE);
        hide.setText("管理");
        //RadioButton ViewPager
        collection_rg = (RadioGroup) findViewById(R.id.collection_rg);
        collection_vp = (ViewPager) findViewById(R.id.collection_vp);
        collection_rg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        for (int i = 0; i < collection_rg.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) collection_rg.getChildAt(i);
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
