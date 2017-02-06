package com.example.dailystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.util.DataClearManager;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoRelativeLayout setting_clear;
    private TextView setting_textview_cachesize;
    private File cacheDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setting_clear = (AutoRelativeLayout) findViewById(R.id.setting_clear);
        setting_textview_cachesize = (TextView) findViewById(R.id.setting_textview_cachesize);
        setting_clear.setOnClickListener(this);
        cacheDir = this.getCacheDir();
        try {
            long folderSize = DataClearManager.getFolderSize(cacheDir);
            String formatSize = DataClearManager.getFormatSize(folderSize);
            setting_textview_cachesize.setText(formatSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_clear:
                DataClearManager.deleteCache(cacheDir);
                try {
                    long folderSize = DataClearManager.getFolderSize(cacheDir);
                    String formatSize = DataClearManager.getFormatSize(folderSize);
                    setting_textview_cachesize.setText(formatSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
