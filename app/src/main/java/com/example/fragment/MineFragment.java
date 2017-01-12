package com.example.fragment;

import android.view.View;

import com.example.base.BaseFragment;
import com.example.dailystudy.R;
import com.example.util.CommonUtils;
import com.example.view.JudgeShowView;


/**
 * Created by lenovo on 2017/1/11.
 */

public class MineFragment extends BaseFragment {


    //private int status;
    private View view;

    /*@Override
    protected void getStatus(int statusNoNetwork) {
        this.status = statusNoNetwork;
    }*//*

    @Override
    protected View setNoSuccessView(int status) {
        return null;
    }

    @Override
    protected View setSuccessView() {
        view = CommonUtils.inflate(R.layout.fragment_mine);

        return view;
    }*/

    @Override
    protected View setDifferentView(int status) {
        view = CommonUtils.inflate(R.layout.activity_mine_fragment);

        return view;
    }

    @Override
    public void onLoad() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setViewStatus(JudgeShowView.StatusType.STATUS_SUCCESS);
            }
        }.start();
    }

}
