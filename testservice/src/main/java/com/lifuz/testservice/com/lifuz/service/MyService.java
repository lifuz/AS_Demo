package com.lifuz.testservice.com.lifuz.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 第一次测试service组件
 *
 * 测试Service的生命周期
 * 即操作对应的生命周期
 *
 * 作者：李富 on 2015/8/7.
 * 邮箱：lifuzz@163.com
 */
public class MyService extends Service {

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
        return null;
    }

}
