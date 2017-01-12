package com.example.dailystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PersonalInformationActivity extends AppCompatActivity {

    private TextView titlename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        //修改标题名
        titlename = (TextView) findViewById(R.id.titlename);
        titlename.setText("个人信息");

    }
}
