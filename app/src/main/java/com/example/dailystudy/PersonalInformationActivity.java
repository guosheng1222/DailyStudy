package com.example.dailystudy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PersonalInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView titlename;
    private ImageView p_img;
    private TextView p_name;
    private TextView p_phone;
    private CheckBox p_boy,p_girl;
    private Button exist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        //修改标题名
        titlename = (TextView) findViewById(R.id.titlename);
        titlename.setText("个人信息");

        p_img = (ImageView) findViewById(R.id.p_img);
        p_name =(TextView)  findViewById(R.id.p_name);
        p_phone = (TextView) findViewById(R.id.p_phone);
        p_boy = (CheckBox) findViewById(R.id.p_boy);
        p_girl = (CheckBox) findViewById(R.id.p_girl);
        exist = (Button) findViewById(R.id.exist);

        String name = getIntent().getStringExtra("name");
        String sex = getIntent().getStringExtra("sex");
        String img = getIntent().getStringExtra("img");

        Glide.with(PersonalInformationActivity.this).load(img).into(p_img);
        p_name.setText(name);
        p_phone.setText(name);

        if(sex.equals("1")){
           p_boy.setChecked(true);
        }else if(sex.equals("2")){
            p_girl.setChecked(true);
        }

        p_boy.setOnClickListener(this);
        p_girl.setOnClickListener(this);
        exist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.p_boy:
                p_girl.setChecked(false);
                break;
            case R.id.p_girl:
                p_boy.setChecked(false);
                break;
            case R.id.exist:
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear();
                edit.commit();
                finish();
                break;
        }
    }
}
