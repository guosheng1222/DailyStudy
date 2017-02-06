package com.example.dailystudy;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.adapter.ThreeBaseAdapter;
import com.example.base.BaseData;
import com.example.bean.CourseData;
import com.example.bean.ThreeList;
import com.example.util.CommonUtils;
import com.example.util.UrlUtils;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoRelativeLayout;


public class CateDetailedActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoRelativeLayout rl_pop1;
    private AutoRelativeLayout rl_pop2;
    private AutoRelativeLayout rl_pop3;
    private TextView tv_pop1;
    private TextView tv_pop2;
    private TextView tv_pop3;
    private ImageView iv_pop1;
    private ImageView iv_pop2;
    private ImageView iv_pop3;
    private View layout1;
    private ListView list_first;
    private ListView list_second;
    private ListView list_third;
    private ThreeBaseAdapter thirdAdapter;
    private int firstI;
    private int secondI;
    private ListView class_listView;
    private String name;
    private String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate_detailed);

        //接收传过来的cid值
        cid = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        Log.i("TAG", "cid--" + cid);
        Log.i("TAG", "name--" + name);


        initView();

        showListData();


        //popupwindow课程列表
        couseList();
    }

    /**
     * 展现list数据
     */
    private void showListData() {

        BaseData baseData = new BaseData() {
            @Override
            public void setResultData(String data) {
                CourseData courseData = new Gson().fromJson(data, CourseData.class);
            }

            @Override
            public void setResultError() {

            }
        };
        baseData.getData(CateDetailedActivity.this,UrlUtils.COURSE_HOME,
                UrlUtils.COURSE_ARGS,0,BaseData.NORMALTIME);

    }


    /**
     * 课程列表
     */
    private void couseList() {
        rl_pop1.setOnClickListener(this);
    }

    private void initView() {
        rl_pop1 = (AutoRelativeLayout) findViewById(R.id.rl_pop1);
        rl_pop2 = (AutoRelativeLayout) findViewById(R.id.rl_pop2);
        rl_pop3 = (AutoRelativeLayout) findViewById(R.id.rl_pop3);

        tv_pop1 = (TextView) findViewById(R.id.tv_pop1);
        tv_pop2 = (TextView) findViewById(R.id.tv_pop2);
        tv_pop3 = (TextView) findViewById(R.id.tv_pop3);
        tv_pop1.setText(name);

        iv_pop1 = (ImageView) findViewById(R.id.iv_pop1);
        iv_pop2 = (ImageView) findViewById(R.id.iv_pop2);
        iv_pop3 = (ImageView) findViewById(R.id.iv_pop3);

        class_listView = (ListView) findViewById(R.id.class_listView);
    }


    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_pop1:

                //查找popupWindow的布局文件
                if (layout1 == null) {
                    layout1 = CommonUtils.inflate(R.layout.pop_third_list);
                    list_first = (ListView) layout1.findViewById(R.id.list_first);
                    list_second = (ListView) layout1.findViewById(R.id.list_second);
                    list_third = (ListView) layout1.findViewById(R.id.list_third);
                }

                //创建对象，参数:控制弹出的大小
                PopupWindow popupWindow = new PopupWindow(layout1,
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAsDropDown(rl_pop1);
                //设置背景变暗
                // backgroundAlpha(100);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //归零
                        firstI = 0;
                        secondI = 0;
                    }
                });

                new BaseData() {

                    private ThreeList[] threeLists;

                    @Override
                    public void setResultData(String data) {

                        threeLists = new Gson().fromJson(data, ThreeList[].class);
                        thirdAdapter = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 1, 0, 0);
                        list_first.setAdapter(thirdAdapter);
                        thirdAdapter = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 2, 0, 0);
                        list_second.setAdapter(thirdAdapter);
                        thirdAdapter = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 3, 0, 0);
                        list_third.setAdapter(thirdAdapter);
                        list_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                View lastView = adapterView.getChildAt(firstI);
                                lastView.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                                view.setBackgroundColor(getResources().getColor(R.color.bg_list2));
                                thirdAdapter = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 2, i, 0);
                                list_second.setAdapter(thirdAdapter);

                                tv_pop1.setText(threeLists[i].getMenu().getCategory_name());
                                CateDetailedActivity.this.firstI = i;

                            }
                        });
                        list_second.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                View lastView = adapterView.getChildAt(secondI);
                                lastView.setBackgroundColor(getResources().getColor(R.color.bg_list2));
                                view.setBackgroundColor(getResources().getColor(R.color.bg_list3));
                                thirdAdapter = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 3, firstI, i);
                                list_third.setAdapter(thirdAdapter);
                                CateDetailedActivity.this.secondI = i;
                            }
                        });
                    }

                    @Override
                    public void setResultError() {

                    }
                }.getData(CateDetailedActivity.this, UrlUtils.three_path, null, 1, BaseData.NORMALTIME);


                break;

        }
    }


    /**
     * 背景变暗
     *
     * @param bgAlpha 变暗程度
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

}
