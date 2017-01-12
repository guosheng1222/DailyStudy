package com.example.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.view.JudgeShowView;


/**
 * Created by lenovo on 2017/1/11.
 */

public abstract class BaseFragment extends Fragment {

    private JudgeShowView judgeShowView;
    private ViewGroup rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        rootView = new FrameLayout(getActivity());

        judgeShowView = new JudgeShowView(getContext()) {
            @Override
            protected void onLoad() {
                BaseFragment.this.onLoad();
            }

            @Override
            public void setDifferentView(int status) {

                View view = BaseFragment.this.setDifferentView(status);
                rootView.addView(view);
            }


        };


        return rootView;
    }

    protected abstract View setDifferentView(int status);


    public abstract void onLoad();

    public void setViewStatus(JudgeShowView.StatusType statusType) {
        if (judgeShowView != null) {
            judgeShowView.setViewStatus(statusType);
        }
    }


}



