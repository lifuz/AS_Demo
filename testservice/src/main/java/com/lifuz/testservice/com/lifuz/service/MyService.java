package com.lifuz.testservice.com.lifuz.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 第一次测试service组件
 *
 * 测试Service的生命周期
 * 即操作对应的生命周期
 *
 * 第二次测试Service组件
 *
 * 主要测试一些使用绑定的方式来启动Service
 *
 * 作者：李富 on 2015/8/7.
 * 邮箱：lifuzz@163.com
 */
public class MyService extends Service {

    private MyBinder mbind = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("tag","onCreate() executed");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("tag","onStartCommand() executed");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        Log.i("tag","onDestroy() executed");

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mbind;
    }


    /**
     * 当使用绑定启动Service的时候需绑定一个类
     * 下面的这个类就是我们将要绑定的类
     */
    public class MyBinder extends Binder{

        /**
         * 这个方法是模拟一个下载方法
         * 下载的连接有Activity赋值
         * @param url
         */
        public void startDownload(String url){
            Log.i("tag","开始下载URL为：" + url + "  的数据");
        }

    }

}
