package com.example.fragment;

import android.util.Log;
import android.view.View;

import com.example.base.BaseFragment;
import com.example.dailystudy.R;
import com.example.view.JudgeShowView;


/**
 * Created by lenovo on 2017/1/11.
 */

public class HomePagerFragment extends BaseFragment {

    private View view;
    //private int status ;


    @Override
    protected View setDifferentView(int status) {
        view = View.inflate(getActivity(), R.layout.fragment_category,null);
        View category_loading = view.findViewById(R.id.category_loading);
        View category_error = view.findViewById(R.id.category_error);
        View category_content = view.findViewById(R.id.category_content);

        Log.i("TAG", "home_status--" + status);

        if (status == JudgeShowView.STATUS_LOADING) {
            category_error.setVisibility(View.GONE);
            category_content.setVisibility(View.GONE);
            category_loading.setVisibility(View.VISIBLE);
        } else if (status == JudgeShowView.STATUS_NO_NETWORK) {
            category_error.setVisibility(View.VISIBLE);
            category_content.setVisibility(View.GONE);
            category_loading.setVisibility(View.GONE);
        } else if (status == JudgeShowView.STATUS_SUCCESS) {
            category_error.setVisibility(View.GONE);
            category_content.setVisibility(View.VISIBLE);
            category_loading.setVisibility(View.GONE);
        }
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
                setViewStatus(JudgeShowView.StatusType.STATUS_LOADING);
            }
        }.start();
    }


}
