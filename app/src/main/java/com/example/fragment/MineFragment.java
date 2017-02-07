package com.example.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app.MyApplication;
import com.example.base.BaseFragment;
import com.example.dailystudy.CollectionActivity;
import com.example.dailystudy.DisCountActivity;
import com.example.dailystudy.FeedBackActivity;
import com.example.dailystudy.LandActivity;
import com.example.dailystudy.MyMessageActivity;
import com.example.dailystudy.MyProjectActivity;
import com.example.dailystudy.PersonalInformationActivity;
import com.example.dailystudy.R;
import com.example.dailystudy.SettingActivity;
import com.example.util.CommonUtils;
import com.example.view.CircleImageView;
import com.example.view.JudgeShowView;
import com.zhy.autolayout.AutoLinearLayout;


/**
 * Created by lenovo on 2017/1/11.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private AutoLinearLayout setting,feedback,mymessage,discount,collection,myproject;
    private CircleImageView head_iamge;
    private Button btn_land;
    private AutoLinearLayout mf_land_body;
    private AutoLinearLayout mf_user;
    private TextView mf_username;
    private CircleImageView mf_image;
    private ImageView mf_more;
    private String name;
    private String sex;
    private SharedPreferences shared;
    private String Myiamges;


    @Override
    protected View setSuccessView(int statusCurrent) {

        view = CommonUtils.inflate(R.layout.activity_mine_fragment);
        setting = (AutoLinearLayout) view.findViewById(R.id.setting);
        feedback = (AutoLinearLayout) view.findViewById(R.id.feedback);
        mymessage = (AutoLinearLayout) view.findViewById(R.id.mymessage);
        discount = (AutoLinearLayout) view.findViewById(R.id.discount);
        collection = (AutoLinearLayout) view.findViewById(R.id.collection);
        myproject = (AutoLinearLayout) view.findViewById(R.id.myproject);
        head_iamge = (CircleImageView) view.findViewById(R.id.head_iamge);
        btn_land = (Button) view.findViewById(R.id.btn_land);

        mf_username = (TextView) view.findViewById(R.id.mf_username);
        mf_more = (ImageView) view.findViewById(R.id.mf_more);

        shared = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
        mf_land_body = (AutoLinearLayout) view.findViewById(R.id.mf_land_body);
        mf_image = (CircleImageView) view.findViewById(R.id.mf_image);
        mf_user = (AutoLinearLayout) view.findViewById(R.id.mf_user);
        mf_username = (TextView) view.findViewById(R.id.mf_username);
        name = shared.getString("name",null);
        sex = shared.getString("sex",null);
        Myiamges = shared.getString("image", null);
        if(name ==null){
            mf_land_body.setVisibility(View.VISIBLE);
            mf_user.setVisibility(View.GONE);
        }else{
            MyApplication.isUser=true;
            mf_user.setVisibility(View.VISIBLE);
            mf_land_body.setVisibility(View.GONE);
            mf_username.setText(name);
            Glide.with(getActivity()).load(Myiamges).into(mf_image);
        }
        setting.setOnClickListener(this);
        feedback.setOnClickListener(this);
        mymessage.setOnClickListener(this);
        discount.setOnClickListener(this);
        collection.setOnClickListener(this);
        myproject.setOnClickListener(this);
        head_iamge.setOnClickListener(this);
        btn_land.setOnClickListener(this);
        mf_user.setOnClickListener(this);
        return view;
    }

    @Override
    protected View setDifferentView(int status) {
        view = CommonUtils.inflate(R.layout.activity_mine_fragment);

        return view;
    }

    @Override
    public void onLoad() {
        judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //设置
            case R.id.setting:
                JumpActivity(SettingActivity.class);
                break;
            //意见反馈
            case R.id.feedback:
                JumpActivity(FeedBackActivity.class);
                break;
            //我的消息
            case R.id.mymessage:
                if(MyApplication.isUser){
                    JumpActivity(MyMessageActivity.class);
                }else{
                    JumpActivity(LandActivity.class);
                }
                break;
            //优惠券
            case R.id.discount:
                if(MyApplication.isUser){
                    JumpActivity(DisCountActivity.class);
                }else{
                    JumpActivity(LandActivity.class);
                }
                break;
            //收藏
            case R.id.collection:
                if(MyApplication.isUser){
                    JumpActivity(CollectionActivity.class);
                }else{
                    JumpActivity(LandActivity.class);
                }
                break;
            //我的课程
            case R.id.myproject:
                if(MyApplication.isUser){
                    JumpActivity(MyProjectActivity.class);
                }else{
                    JumpActivity(LandActivity.class);
                }
                break;
            //我的头像
            case R.id.head_iamge:
                JumpActivity(LandActivity.class);
                break;
            //登录
            case R.id.btn_land:
                JumpActivity(LandActivity.class);
                break;

            case R.id.mf_user:
                Intent intent=new Intent(getActivity(),PersonalInformationActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("sex",sex);
                intent.putExtra("img",Myiamges);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        onLoad();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void JumpActivity(Class c) {
        Intent intent=new Intent(getActivity(),c);
        startActivity(intent);
    }
}
