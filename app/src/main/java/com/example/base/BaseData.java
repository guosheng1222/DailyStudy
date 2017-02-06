package com.example.base;

import android.content.Context;
import android.text.TextUtils;

import com.example.util.CommonUtils;
import com.example.util.LogUtils;
import com.example.util.MD5Encoder;
import com.example.util.NetUtils;
import com.sunshine.retrofit.HttpUtil;
import com.sunshine.retrofit.interfaces.Error;
import com.sunshine.retrofit.interfaces.Success;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Set;

import static com.example.util.NetUtils.NET_WORK_TYPE_INVALID;


/**
 * Created by PC on 2017/1/11.
 */

public abstract class BaseData {
    public static final int NOTIME = 0;
    public static final int NORMALTIME = 3 * 24 * 60 * 60 * 1000;
    private final File fileDir;
    private Context mContext;

    public BaseData() {
        File cacheDir = CommonUtils.getContext().getCacheDir();
        fileDir = new File(cacheDir, "DailyStudy");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
    }

    public void getData(Context context, String path, String args, int index, int validTime) {
        this.mContext = context;
        //如果有效时间为0
        if (validTime == 0) {
            //判断是否有网   有就请求数据
            if (getIsNoNet()) {
                //从网络请求数据
                getDataFromNet(path, args, index, validTime);
            } else {
                setResultError();
            }
        } else {
            //从本地获取数据
            String data = getDataFromLocal(path,index, validTime);
            //如果本地没有数据
            if (TextUtils.isEmpty(data)) {
                if (getIsNoNet()) {
                    //从网络请求数据
                    getDataFromNet(path, args, index, validTime);
                } else {
                    setResultError();
                }
            } else {
                //返回数据
                setResultData(data);
            }
        }
    }
    /**
     * post请求
     */
    public void postData(Context context, String path, HashMap<String,String> argsMap, int index, int validTime) {
        this.mContext = context;
        //如果有效时间为0
        if (validTime == 0) {
            //判断是否有网   有就请求数据
            if (getIsNoNet()) {
                //从网络请求数据
                postDataFromNet(path, argsMap, stringBuilder.toString(),index, validTime);
            } else {
                setResultError();
            }
        } else {
            //从本地获取数据
            String data = getDataFromLocal(path,index,validTime);
            //如果本地没有数据
            if (TextUtils.isEmpty(data)) {
                if (getIsNoNet()) {
                    //从网络请求数据
                    postDataFromNet(path,argsMap, stringBuilder.toString(), index, validTime);
                } else {
                    setResultError();
                }
            } else {
                //返回数据
                setResultData(data);
            }
        }
    }

    /**
     * 是否有网
     */
    private boolean getIsNoNet() {
        //判断当前的网络状态
        if (NET_WORK_TYPE_INVALID != NetUtils.getNetWorkType(mContext)) {
            return true;
        }
        return false;
    }

    public abstract void setResultData(String data);

    public abstract void setResultError();

    /**
     * 从本地请求数据
     */
    private String getDataFromLocal(String path, int index, int validTime) {
        try {
            File file = new File(fileDir, MD5Encoder.encode(path) + index);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = bufferedReader.readLine();
            long l = Long.parseLong(s);
            if (System.currentTimeMillis() < l) {
                StringBuilder builder = new StringBuilder();
                String lin = null;
                while ((lin = bufferedReader.readLine()) != null) {
                    builder.append(lin);
                }
                bufferedReader.close();
                return builder.toString();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从网络请求数据 GET
     */
    private void getDataFromNet(final String path, final String args, final int index, final int validTime) {
        new HttpUtil.Builder(path)
                .Success(new Success() {
                    @Override
                    public void Success(String model) {
                        setResultData(model);
                        writeDataToLocal(path,index,validTime,model);
                    }
                })
                .Error(new Error() {
                    @Override
                    public void Error(Object... objects) {
                        setResultError();
                    }
                }).get();

    }

    /**
     * 从网络请求数据 Post
     */
    private void postDataFromNet(final String path, final HashMap<String,String> argsMap, final String args,final int index, final int validTime) {
        new HttpUtil.Builder(path)
                .Params(argsMap)
                .Success(new Success() {
                    @Override
                    public void Success(String model) {
                        setResultData(model);
                        writeDataToLocal(path,index,validTime,model);
                        LogUtils.d("lllllll","**********"+model);
                        writeDataToLocal(path+args,index,validTime,model);
                    }
                })
                .Error(new Error() {
                    @Override
                    public void Error(Object... objects) {
                        setResultError();
                    }
                })
                .post();
    }

    /**
     * 把数据缓存到本地
     */
    private void writeDataToLocal(String path, int index, int validTime, String data) {
        try {
            File file = new File(fileDir, MD5Encoder.encode(path) + index);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(System.currentTimeMillis() + validTime + "\r\n");
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
