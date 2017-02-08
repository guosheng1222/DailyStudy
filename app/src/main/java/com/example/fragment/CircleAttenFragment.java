package com.example.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app.MyApplication;
import com.example.base.BaseFragment;
import com.example.dailystudy.LandActivity;
import com.example.dailystudy.R;
import com.example.manager.ChangeHideManager;
import com.example.view.JudgeShowView;

/**
 * Created by PC on 2017/1/12.
 */

public class CircleAttenFragment extends BaseFragment {

    private View view;
    private RelativeLayout wuAtten;
    private RelativeLayout loaded;
    private TextView gotoLoad;

    @Override
    protected View setSuccessView(int statusCurrent) {
        view = View.inflate(getActivity(),R.layout.circle_atten_fragment,null);
        ChangeHideManager.changeVisible(view, statusCurrent);
        return view;
    }

    @Override
    protected View setDifferentView(int status) {
        view = View.inflate(getActivity(),R.layout.circle_atten_fragment,null);
        ChangeHideManager.changeVisible(view, status);
        gotoLoad = (TextView) view.findViewById(R.id.gotoLoad);
        gotoLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),LandActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout no_network = (LinearLayout) view.findViewById(R.id.no_network);
        return view;
    }

    @Override
    public void onLoad() {
        judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_LOADING);
        if(MyApplication.isUser){
            judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_SUCCESS);
        }else{
            judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_NO_NETWORK);
        }
    }
}
