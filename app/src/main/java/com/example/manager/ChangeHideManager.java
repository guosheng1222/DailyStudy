package com.example.manager;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import com.example.dailystudy.R;
import com.example.util.CommonUtils;
import com.example.view.JudgeShowView;

/**
 * Created by lenovo on 2017/1/12.
 */

public class ChangeHideManager {


    public static void changeVisible(View view, int status) {


        View loading = view.findViewById(R.id.loading);
        View no_network = view.findViewById(R.id.no_network);
        View content = view.findViewById(R.id.content);

        if (status == JudgeShowView.STATUS_LOADING) {
            no_network.setVisibility(View.GONE);
            content.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);

            View loading_layout = CommonUtils.inflate(R.layout.loading_layout);
            ImageView iv_loading = (ImageView) loading_layout.findViewById(R.id.iv_loading);
            AnimationDrawable drawable = (AnimationDrawable) iv_loading.getDrawable();
            drawable.start();
        } else if (status == JudgeShowView.STATUS_NO_NETWORK) {
            no_network.setVisibility(View.VISIBLE);
            content.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
        } else if (status == JudgeShowView.STATUS_SUCCESS) {
            no_network.setVisibility(View.GONE);
            content.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }

}
