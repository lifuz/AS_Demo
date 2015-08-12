package com.lifuz.testservice.com.lifuz.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

    private static final String TAG = "MainActivity";

    @Override
    public void onCreate() {
        super.onCreate();

        final IntentFilter filter = new IntentFilter();
        // 屏幕灭屏广播
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        // 屏幕亮屏广播
        filter.addAction(Intent.ACTION_SCREEN_ON);
        // 屏幕解锁广播
        filter.addAction(Intent.ACTION_USER_PRESENT);
        // 当长按电源键弹出“关机”对话或者锁屏时系统会发出这个广播
        // example：有时候会用到系统对话框，权限可能很高，会覆盖在锁屏界面或者“关机”对话框之上，
        // 所以监听这个广播，当收到时就隐藏自己的对话，如点击pad右下角部分弹出的对话框
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                Log.d(TAG, "onReceive");
                String action = intent.getAction();

                if (Intent.ACTION_SCREEN_ON.equals(action)) {
                    Log.d(TAG, "亮屏");
                } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                    Log.d(TAG, "灭屏");
                } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
                    Log.d(TAG, "解锁");
                } else if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(intent.getAction())) {
                    Log.i(TAG, " receive Intent.ACTION_CLOSE_SYSTEM_DIALOGS");
                }
            }
        };
        Log.d(TAG, "registerReceiver");
        registerReceiver(mBatInfoReceiver, filter);

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
