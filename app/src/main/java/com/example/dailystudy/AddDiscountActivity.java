package com.example.dailystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AddDiscountActivity extends AppCompatActivity {

    private TextView titlename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount);

        titlename = (TextView) findViewById(R.id.titlename);
        titlename.setText("添加优惠券");
    }
}
