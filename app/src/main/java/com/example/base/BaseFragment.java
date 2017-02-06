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

    public JudgeShowView judgeShowView;
    private ViewGroup rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = new FrameLayout(getActivity());
        }
        judgeShowView = new JudgeShowView(getActivity()) {
            @Override
            protected void onLoad() {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(200);
                            BaseFragment.this.onLoad();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }

            @Override
            public void setDifferentView(int status) {
                rootView.removeAllViews();
                View view = BaseFragment.this.setDifferentView(status);
                rootView.addView(view);
            }

            @Override
            public void setSuccessView(int statusCurrent) {
                rootView.removeAllViews();
                View view = BaseFragment.this.setSuccessView(statusCurrent);
                rootView.addView(view);
            }
        };
        return rootView;
    }

    protected abstract View setSuccessView(int statusCurrent);
    protected abstract View setDifferentView(int status);
    public abstract void onLoad();


}



