package com.example.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.sunshine.retrofit.HttpUtil;
import com.sunshine.retrofit.interfaces.HeadersInterceptor;
import com.sunshine.retrofit.interfaces.ParamsInterceptor;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.Map;

/**
 * Created by lenovo on 2017/1/11.
 */

public class MyApplication extends Application {

    private static Context context;
    private static int mainThreadId;
    private static Handler handler;
    public static boolean isLand=false;
    public static boolean isUser=false;

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();
        //获取当前应用的上下文
        context = getApplicationContext();
        handler = new Handler();
        //获取主线程的线程号
        mainThreadId = Process.myTid();
        //xutils3初始化配置
        ParamsInterceptor mParamsInterceptor = new ParamsInterceptor() {
            @Override
            public Map checkParams(Map params) {
                return params;
            }
        };
        HeadersInterceptor mHeadersInterceptor = new HeadersInterceptor() {
            @Override
            public Map checkHeaders(Map headers) {
                //追加统一header，例：数据缓存一天
                headers.put("Cache-Time", "3600*24");
                return headers;
            }
        };
        new HttpUtil.SingletonBuilder(getApplicationContext())
                .baseUrl("http://www.meirixue.com")//URL请求前缀地址。必传
//                .versionApi("")//API版本，不传不可以追加接口版本号
//                .addServerUrl("")//备份服务器ip地址，可多次调用传递
//                .addCallFactory()//不传默认StringConverterFactory
//                .addConverterFactory()//不传默认RxJavaCallAdapterFactory
//                .client()//OkHttpClient,不传默认OkHttp3
                .paramsInterceptor(mParamsInterceptor)//不传不进行参数统一处理
//               .headersInterceptor(mHeadersInterceptor)//不传不进行headers统一处理
                .build();

    }


    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程
     *
     * @return
     */
    public static Thread getMainThread() {
        return Thread.currentThread();
    }
    /**
     * 获取整个应用的上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

}
