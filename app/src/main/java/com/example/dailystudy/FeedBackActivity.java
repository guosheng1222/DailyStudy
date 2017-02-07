package com.example.dailystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class FeedBackActivity extends AppCompatActivity {

    private TextView titlename;
    private TextView hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        //修改标题名
        titlename = (TextView) findViewById(R.id.titlename);
        titlename.setText("意见反馈");
        hide = (TextView) findViewById(R.id.hide);
        hide.setVisibility(View.VISIBLE);
        hide.setText("发送");
    }
}
