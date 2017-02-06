package com.example.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.base.BaseFragment;
import com.example.dailystudy.CollectionActivity;
import com.example.dailystudy.DisCountActivity;
import com.example.dailystudy.FeedBackActivity;
import com.example.dailystudy.LandActivity;
import com.example.dailystudy.MyMessageActivity;
import com.example.dailystudy.MyProjectActivity;
import com.example.dailystudy.R;
import com.example.dailystudy.SettingActivity;
import com.example.manager.ChangeHideManager;
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
        setting.setOnClickListener(this);
        feedback.setOnClickListener(this);
        mymessage.setOnClickListener(this);
        discount.setOnClickListener(this);
        collection.setOnClickListener(this);
        myproject.setOnClickListener(this);
        head_iamge.setOnClickListener(this);
        btn_land.setOnClickListener(this);

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
                JumpActivity(MyMessageActivity.class);
                break;
            //优惠券
            case R.id.discount:
                JumpActivity(DisCountActivity.class);
                break;
            //收藏
            case R.id.collection:
                JumpActivity(CollectionActivity.class);
                break;
            //我的课程
            case R.id.myproject:
                JumpActivity(MyProjectActivity.class);
                break;
            //我的头像
            case R.id.head_iamge:
                JumpActivity(LandActivity.class);
                break;
            //登录
            case R.id.btn_land:
                JumpActivity(LandActivity.class);
                break;
        }
    }

    private void JumpActivity(Class c) {
        Intent intent=new Intent(getActivity(),c);
        startActivity(intent);
    }
}
