package com.example.dailystudy;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.adapter.CourseAdapter;
import com.example.adapter.ThreeBaseAdapter;
import com.example.base.BaseData;
import com.example.bean.CategoryFace;
import com.example.bean.CourseData;
import com.example.bean.ThreeList;
import com.example.util.CommonUtils;
import com.example.util.ListUtils;
import com.example.util.UrlUtils;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


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
    private ListView class_listView;
    private String name;
    private String cid;

    //定义三个int值来记录三级列表点击的下标
    private int firstIndex = 0;
    private int secondIndex = 0;
    private int ThirdIndex = 0;
    private ArrayList<CategoryFace> cateList;
    private ThreeList[] threeLists;
    private PopupWindow popupWindow;
    private ThreeBaseAdapter thirdAdapter1;
    private ThreeBaseAdapter thirdAdapter2;
    private ThreeBaseAdapter thirdAdapter3;
    private List<CourseData.DatalistBean> datalist;
    private List<CourseData.DatalistBean> sortList = new ArrayList<CourseData.DatalistBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate_detailed);
        //接受intent传过来的值
        receiverMsg();

        //初始化控件
        initView();
        //展示list数据
        showListData();


        //popupwindow课程列表
        couseList();
    }

    /**
     * 初始化操作
     */
    private void init() {
        cateList = ListUtils.cateList;

        for (int i = 0; i < threeLists.length; i++) {
            for (int j = 0; j < threeLists[i].getNodes().size(); j++) {
                for (int k = 0; k < threeLists[i].getNodes().get(j).getNodes2().size(); k++) {
                    if (cid.equals(threeLists[i].getNodes().get(j).getNodes2().get(k).getMenu3().getId())) {
                        firstIndex = i;
                        secondIndex = j;
                        ThirdIndex = k;
                        Log.i("TAG", "ThirdIndex--" + ThirdIndex);
                    }
                }
            }
        }


    }

    private void receiverMsg() {
        //接收传过来的cid值
        Intent intent = getIntent();
        cid = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
    }

    /**
     * 展现list数据
     */
    private void showListData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("cid", cid);
        map.put("p", 0 + "");
        new BaseData() {
            @Override
            public void setResultData(String data) {
                CourseData courseData = new Gson().fromJson(data, CourseData.class);
                datalist = courseData.getDatalist();
                sortList.clear();
                for (int i = 0; i < datalist.size(); i++) {
                    sortList.add(datalist.get(i));
                }
                CourseAdapter courseAdapter = new CourseAdapter(CateDetailedActivity.this, sortList);
                class_listView.setAdapter(courseAdapter);
            }

            @Override
            public void setResultError() {

            }
        }.postData(CateDetailedActivity.this, UrlUtils.COURSE_HOME, map, 0, BaseData.NOTIME);
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

        rl_pop2.setOnClickListener(this);
        rl_pop3.setOnClickListener(this);
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
                //弹出popupWindow
                showFirstPopupWindow();

                //请求数据
                requestNetData();

                break;


            case R.id.rl_pop2:

                initPopupWindow2();
                break;

            case R.id.rl_pop3:
                initPopupWindow3();
                break;
        }
    }

    private void initPopupWindow3() {

        View layout3 = CommonUtils.inflate(R.layout.pop3_layout);
        TextView total_sort = (TextView) layout3.findViewById(R.id.total_sort);
        TextView recently_update = (TextView) layout3.findViewById(R.id.recently_update);
        TextView study_count = (TextView) layout3.findViewById(R.id.study_count);

        final PopupWindow popupWindow3 = new PopupWindow(layout3,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow3.setFocusable(true);
        popupWindow3.setOutsideTouchable(true);
        popupWindow3.setBackgroundDrawable(new BitmapDrawable());
        popupWindow3.showAsDropDown(rl_pop1);

        total_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        recently_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        study_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow3.dismiss();
                Collections.sort(sortList, new Comparator<CourseData.DatalistBean>() {
                    @Override
                    public int compare(CourseData.DatalistBean bean1, CourseData.DatalistBean bean2) {

                        int count1 = Integer.parseInt(bean1.getCourse_paycount());
                        int count2 = Integer.parseInt(bean2.getCourse_paycount());

                        if (count1 > count2) {
                            return -1;
                        } else if (count1 < count2) {
                            return 1;
                        }
                        return 0;
                    }
                });

                CourseAdapter courseAdapter = new CourseAdapter(CateDetailedActivity.this, sortList);
                class_listView.setAdapter(courseAdapter);


            }
        });


    }


    /**
     * 点击rl_pop2
     */
    private void initPopupWindow2() {

        showPopupWindow2();


    }

    /**
     * 弹出popupwindow2
     */
    private void showPopupWindow2() {

        View layout2 = CommonUtils.inflate(R.layout.pop2_layout);
        TextView free = (TextView) layout2.findViewById(R.id.pop2_item_tv_free);
        TextView sale = (TextView) layout2.findViewById(R.id.pop2_item_tv_sale);

        final PopupWindow popupWindow2 = new PopupWindow(layout2,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow2.setFocusable(true);
        popupWindow2.setOutsideTouchable(true);
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.showAsDropDown(rl_pop1);

        /**
         * 点击事件
         */

        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortList.clear();
                popupWindow2.dismiss();
                for (int i = 0; i < datalist.size(); i++) {
                    if ("0.00".equals(datalist.get(i).getCourse_price())) {
                        sortList.add(datalist.get(i));
                    }

                    CourseAdapter courseAdapter = new CourseAdapter(CateDetailedActivity.this, sortList);
                    class_listView.setAdapter(courseAdapter);
                }
            }
        });

        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow2.dismiss();
                sortList.clear();
                for (int i = 0; i < datalist.size(); i++) {
                    if (!"0.00".equals(datalist.get(i).getCourse_price())) {
                        sortList.add(datalist.get(i));
                    }

                    CourseAdapter courseAdapter = new CourseAdapter(CateDetailedActivity.this, sortList);
                    class_listView.setAdapter(courseAdapter);
                }
            }
        });


    }

    private void requestNetData() {
        new BaseData() {

            @Override
            public void setResultData(String data) {

                threeLists = new Gson().fromJson(data, ThreeList[].class);

                //初始化操作
                init();

                //对第一个PopupWindow做的操作
                initFirstPopupWindow();

            }

            @Override
            public void setResultError() {

            }
        }.getData(CateDetailedActivity.this, UrlUtils.three_path, null, 1, BaseData.NORMALTIME);
    }

    private void showFirstPopupWindow() {
        //查找popupWindow的布局文件
        if (layout1 == null) {
            layout1 = CommonUtils.inflate(R.layout.pop_third_list);
            list_first = (ListView) layout1.findViewById(R.id.list_first);
            list_second = (ListView) layout1.findViewById(R.id.list_second);
            list_third = (ListView) layout1.findViewById(R.id.list_third);
        }

        //创建对象，参数:控制弹出的大小
        popupWindow = new PopupWindow(layout1,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(rl_pop1);
    }


    /**
     * 对第一个PopupWindow做的操作
     */
    private void initFirstPopupWindow() {
        thirdAdapter1 = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 1, firstIndex, secondIndex, ThirdIndex);
        list_first.setAdapter(thirdAdapter1);
        thirdAdapter2 = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 2, firstIndex, secondIndex, ThirdIndex);
        list_second.setAdapter(thirdAdapter2);
        thirdAdapter3 = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 3, firstIndex, secondIndex, ThirdIndex);
        list_third.setAdapter(thirdAdapter3);
        list_first.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //如果点击的不是一个父条目，子条目自动选中第0条
                if (firstIndex != i) {
                    secondIndex = 0;
                    ThirdIndex = 0;
                }
                firstIndex = i;
                thirdAdapter1 = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 1, firstIndex, secondIndex, ThirdIndex);
                list_first.setAdapter(thirdAdapter1);


                thirdAdapter2 = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 2, i, secondIndex, ThirdIndex);
                list_second.setAdapter(thirdAdapter2);

                tv_pop1.setText(threeLists[i].getMenu().getCategory_name());

            }
        });
        list_second.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (secondIndex != i) {
                    ThirdIndex = 0;
                }
                secondIndex = i;

                thirdAdapter2 = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 2,
                        firstIndex, secondIndex, ThirdIndex);
                list_second.setAdapter(thirdAdapter2);


                thirdAdapter3 = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 3,
                        firstIndex, secondIndex, ThirdIndex);
                list_third.setAdapter(thirdAdapter3);
            }
        });

        list_third.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ThirdIndex = i;
                thirdAdapter3 = new ThreeBaseAdapter(CateDetailedActivity.this, threeLists, 3,
                        firstIndex, secondIndex, ThirdIndex);
                list_third.setAdapter(thirdAdapter3);

                cid = threeLists[firstIndex].getNodes().get(secondIndex).getNodes2().get(i).getMenu3().getId();

                //再次请求展现新的数据
                showListData();

                popupWindow.dismiss();

            }
        });
    }


}
