package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by lenovo on 2017/1/16.
 */

public class MyMeasureGridView extends GridView{
    public MyMeasureGridView(Context context) {
        super(context);
    }

    public MyMeasureGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMeasureGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
