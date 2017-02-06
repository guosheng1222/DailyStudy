package com.example.dailystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.ViewPagerAdapter;
import com.example.base.BaseData;
import com.example.bean.RoolDetail;
import com.example.util.GlideUtils;
import com.example.util.UrlUtils;
import com.example.view.MyGridView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class RoolViewDetailsActivity extends AppCompatActivity {

    private ImageView detail_roolimg;
    private TextView detail_con;
    private TextView detail_title;
    private RoolDetail roolDetail;
    private HashMap<String, String> map;
    private MyGridView detail_gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rool_view_details);

        String url = getIntent().getStringExtra("url");
        map = new HashMap<>();
        map.put("aid",url);

        detail_roolimg = (ImageView) findViewById(R.id.detail_roolimg);
        detail_con = (TextView) findViewById(R.id.detail_con);
        detail_title = (TextView) findViewById(R.id.detail_title);
        detail_gv = (MyGridView) findViewById(R.id.detail_gv);
        detail_gv.setFocusable(false);
        Onload();

    }

    private void Onload() {
        new BaseData() {
            @Override
            public void setResultData(String data) {
                Gson gson=new Gson();
                roolDetail = gson.fromJson(data, RoolDetail.class);
                GlideUtils.loadImageView(RoolViewDetailsActivity.this,roolDetail.getToppic(),detail_roolimg);
                detail_con.setText(roolDetail.getDesc());
                detail_title.setText(roolDetail.getDataList().get(0).getTitle());
                List<RoolDetail.DataListBean.ListBean> list = roolDetail.getDataList().get(0).getList();
                detail_gv.setAdapter(new ViewPagerAdapter(RoolViewDetailsActivity.this,list));
            }

            @Override
            public void setResultError() {

            }
        }.postData(this, UrlUtils.rooldetail,map,0,BaseData.NOTIME);
    }
}
