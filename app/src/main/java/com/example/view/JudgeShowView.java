package com.example.view;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import static com.example.util.CommonUtils.runOnMainThread;


/**
 * Created by lenovo on 2017/1/11.
 */

public abstract class JudgeShowView extends FrameLayout implements View.OnClickListener {

    public static final int STATUS_UNLOAD = 0;
    public static final int STATUS_NO_NETWORK = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_SUCCESS = 3;

    private int statusCurrent = STATUS_UNLOAD;

    public JudgeShowView(Context context) {
        super(context);


        //判断显示的界面
        //showView();
        //加载数据
        onLoad();

    }

    protected abstract void onLoad();

    public abstract View setDifferentView(int status);



    /**
     * 显示View
     */
    private void showView() {

        runOnMainThread(new Runnable() {
            @Override
            public void run() {
                //显示对应的界面
                showUIView();
            }
        });
    }

    /**
     * 显示界面效果
     */
    private void showUIView() {


        if (statusCurrent == STATUS_NO_NETWORK) {
            setDifferentView(STATUS_NO_NETWORK);
        }


        if (statusCurrent == STATUS_LOADING) {
            setDifferentView(STATUS_LOADING);
        }

        if (statusCurrent == STATUS_SUCCESS) {
            setDifferentView(STATUS_SUCCESS);
        }
    }


    @Override
    public void onClick(View view) {
        statusCurrent = STATUS_LOADING;
        showView();
        onLoad();
    }

    public void setViewStatus(StatusType statusType) {
        this.statusCurrent = statusType.getStatusCurrent();
        showView();
    }

    public enum StatusType {

        STATUS_UNLOAD(0), STATUS_NO_NETWORK(1), STATUS_LOADING(2), STATUS_SUCCESS(3);
        private final int statusCurrent;

        StatusType(int statusCurrent) {
            this.statusCurrent = statusCurrent;
        }

        public int getStatusCurrent() {
            return statusCurrent;
        }
    }


}
