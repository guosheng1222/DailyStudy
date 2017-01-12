package com.example.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.view.JudgeShowView;


/**
 * Created by lenovo on 2017/1/11.
 */

public abstract class BaseFragment extends Fragment {

    private JudgeShowView judgeShowView;
    private int status;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        judgeShowView = new JudgeShowView(getContext()) {
            @Override
            protected void onLoad() {
                BaseFragment.this.onLoad();
            }

            @Override
            public View setDifferentView(int status) {
                BaseFragment.this.status = status;
                return BaseFragment.this.setDifferentView(status);
            }


        };


        return BaseFragment.this.setDifferentView(status);
    }

    protected abstract View setDifferentView(int status);



    public abstract void onLoad();

    public void setViewStatus(JudgeShowView.StatusType statusType) {
        if (judgeShowView != null) {
            judgeShowView.setViewStatus(statusType);
        }
    }


}



