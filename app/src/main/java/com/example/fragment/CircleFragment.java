package com.example.fragment;

import android.view.View;

import com.example.base.BaseFragment;
import com.example.dailystudy.R;
import com.example.util.CommonUtils;
import com.example.view.JudgeShowView;


/**
 * Created by lenovo on 2017/1/11.
 */

public class CircleFragment extends BaseFragment {


    //private int status;
    private View view;


    @Override
    protected View setDifferentView(int status) {
        view = CommonUtils.inflate(R.layout.fragment_circle);
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
