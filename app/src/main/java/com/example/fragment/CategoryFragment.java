package com.example.fragment;

import android.view.View;

import com.example.base.BaseFragment;
import com.example.dailystudy.R;
import com.example.manager.ChangeHideManager;
import com.example.util.CommonUtils;
import com.example.view.JudgeShowView;


/**
 * Created by lenovo on 2017/1/11.
 */

public class CategoryFragment extends BaseFragment {

    private View view;


    @Override
    protected View setSuccessView(int statusCurrent) {
        view = CommonUtils.inflate(R.layout.fragment_category);

        ChangeHideManager.changeVisible(view, statusCurrent);

        return view;
    }

    @Override
    protected View setDifferentView(int status) {
        view = CommonUtils.inflate(R.layout.fragment_category);

        ChangeHideManager.changeVisible(view, status);

        return view;
    }

    @Override
    public void onLoad() {
        judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_SUCCESS);
    }


}
