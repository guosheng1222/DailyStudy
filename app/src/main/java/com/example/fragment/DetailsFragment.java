package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.base.BaseData;
import com.example.bean.Course;
import com.example.bean.CourseDetails;
import com.example.dailystudy.R;
import com.example.util.CommonUtils;
import com.example.util.LogUtils;
import com.example.util.UrlUtils;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ASUS on 2017/2/4.
 */
public class DetailsFragment extends Fragment{

    private TextView tv_fragment;
    private AutoLinearLayout details_layout_details;
    private TextView details_tv_teacher;
    private TextView details_tv_scholl;
    private TextView details_tv_highlights;
    private TextView details_tv_brief_introduction;
    private AutoLinearLayout details_layout_catalog;
    private TextView details_fragment_tv_title;
    private ListView details_fragment_lv;
    private String url;
    private Course course;
    private CourseDetails courseDetails;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.details_fragment, null);
        // 详情
        details_layout_details = (AutoLinearLayout) view.findViewById(R.id.details_layout_details);
        details_tv_teacher = (TextView) view.findViewById(R.id.details_tv_teacher);
        details_tv_scholl = (TextView) view.findViewById(R.id.details_tv_scholl);
        details_tv_highlights = (TextView) view.findViewById(R.id.details_tv_Highlights);
        details_tv_brief_introduction = (TextView) view.findViewById(R.id.details_tv_brief_introduction);
        //目录
        details_layout_catalog = (AutoLinearLayout) view.findViewById(R.id.details_layout_catalog);
        details_fragment_lv = (ListView) view.findViewById(R.id.details_fragment_lv);
        details_fragment_tv_title = (TextView) view.findViewById(R.id.details_fragment_tv_title);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String type = getArguments().getString("s");
        course = (Course) getArguments().getSerializable("bean");
        url = getArguments().getString("url");
        initData();
        if(type.equals("详情")){
            details_layout_details.setVisibility(View.VISIBLE);
            details_layout_catalog.setVisibility(View.GONE);
        } else if(type.equals("目录")){
            details_layout_details.setVisibility(View.GONE);
            details_layout_catalog.setVisibility(View.VISIBLE);
        }
        details_tv_teacher.setText("讲师:"+course.getData().getCourse_tname());
        details_tv_scholl.setText("学校:"+course.getData().getSchool_name());
        details_tv_highlights.setText(course.getData().getCourse_tb_bright());
        details_tv_brief_introduction.setText(course.getData().getCourse_tb_desc());
    }

    private void initData() {
        HashMap<String,String> map = new HashMap<>();
        map.put("courseid",url);
        new BaseData(){

            @Override
            public void setResultData(String data) {
                LogUtils.d("setResultData+++",data);
                Gson gson=new Gson();
                courseDetails = gson.fromJson(data, CourseDetails.class);
                details_fragment_tv_title.setText("第一章:"+courseDetails.getData().get(0).getStep_name());
                final List<CourseDetails.DataBean.NodesBean> nodes = courseDetails.getData().get(0).getNodes();
                CommonUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        details_fragment_lv.setAdapter(new BaseAdapter() {
                            @Override
                            public int getCount() {
                                return nodes.size();
                            }

                            @Override
                            public Object getItem(int i) {
                                return null;
                            }

                            @Override
                            public long getItemId(int i) {
                                return 0;
                            }

                            @Override
                            public View getView(int i, View view, ViewGroup viewGroup) {
                                if(view == null){
                                    view = LayoutInflater.from(getActivity()).inflate(R.layout.details_fragment_lv_item,null);
                                }
                                TextView lv_tv_title = (TextView) view.findViewById(R.id.lv_tv_title);
                                TextView lv_tv_name = (TextView) view.findViewById(R.id.lv_tv_name);
                                TextView lv_tv_time = (TextView) view.findViewById(R.id.lv_tv_time);
                                lv_tv_title.setText(nodes.get(i).getSections_isfree()+"-"+nodes.get(i).getSections_sort());
                                lv_tv_name.setText(nodes.get(i).getSections_name());
                                return view;
                            }
                        });
                    }
                });


            }

            @Override
            public void setResultError() {

            }
        }.postData(getActivity(), UrlUtils.kechengdetails,map,0,BaseData.NOTIME);

    }

    public static Fragment getFragment(String s, Course course, String url) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("s", s);
        bundle.putSerializable("bean", (Serializable) course);
        bundle.putString("url",url);
        fragment.setArguments(bundle);
        return fragment;
    }
}
