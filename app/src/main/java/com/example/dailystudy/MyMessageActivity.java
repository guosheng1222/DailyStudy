package com.example.dailystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MyMessageActivity extends AppCompatActivity {

    private TextView titlename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        //修改标题名
        titlename = (TextView) findViewById(R.id.titlename);
        titlename.setText("消息中心");
    }
}
