package com.example.dailystudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DisCountActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView titlename;
    private TextView hide;
    private TextView look_discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_count);
        //修改标题名
        titlename = (TextView) findViewById(R.id.titlename);
        titlename.setText("优惠券");
        hide = (TextView) findViewById(R.id.hide);
        hide.setVisibility(View.VISIBLE);
        hide.setText("+添加");
        //查看历史优惠券
        look_discount = (TextView) findViewById(R.id.look_discount);
        hide.setOnClickListener(this);
        look_discount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hide:
                JumpActivity(AddDiscountActivity.class);
                break;
            case R.id.look_discount:
                JumpActivity(HistoryDiscountActivity.class);
                break;
        }
    }

    private void JumpActivity(Class c) {
        Intent intent=new Intent(DisCountActivity.this,c);
        startActivity(intent);
    }
}
