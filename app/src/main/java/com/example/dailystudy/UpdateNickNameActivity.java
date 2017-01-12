package com.example.dailystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class UpdateNickNameActivity extends AppCompatActivity {

    private TextView titlename;
    private TextView hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nick_name);
        //修改标题名
        titlename = (TextView) findViewById(R.id.titlename);
        titlename.setText("修改昵称");
        hide = (TextView) findViewById(R.id.hide);
        hide.setVisibility(View.VISIBLE);
        hide.setText("保存");

    }
}
