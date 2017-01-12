package com.example.fragment;

import android.util.Log;
import android.view.View;

import com.example.base.BaseFragment;
import com.example.dailystudy.R;
import com.example.manager.ChangeHideManager;
import com.example.view.JudgeShowView;


/**
 * Created by lenovo on 2017/1/11.
 */

public class HomePagerFragment extends BaseFragment {

    private View view;
    //private int status ;


    @Override
    protected View setDifferentView(int status) {
        view = View.inflate(getActivity(), R.layout.fragment_homepage, null);

        ChangeHideManager.changeVisible(view, status);

        return view;
    }

    @Override
    public void onLoad() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setViewStatus(JudgeShowView.StatusType.STATUS_SUCCESS);
            }
        }.start();
    }


}
