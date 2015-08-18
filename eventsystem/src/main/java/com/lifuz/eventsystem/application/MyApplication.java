package com.lifuz.eventsystem.application;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.lifuz.eventsystem.service.MyService;

/**
 * 作者：李富 on 2015/8/18.
 * 邮箱：lifuzz@163.com
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final IntentFilter filter = new IntentFilter();
        // 屏幕灭屏广播
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        // 当长按电源键弹出“关机”对话或者锁屏时系统会发出这个广播
        // example：有时候会用到系统对话框，权限可能很高，会覆盖在锁屏界面或者“关机”对话框之上，
        // 所以监听这个广播，当收到时就隐藏自己的对话，如点击pad右下角部分弹出的对话框
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                    Log.d("service", "灭屏");

                    if (MyService.isRunning) {

                        stopService(new Intent(MyApplication.this,MyService.class));

                    }

                }else if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(intent.getAction())) {
                    Log.i("service", "home");

                    if (MyService.isRunning) {

                        stopService(new Intent(MyApplication.this,MyService.class));

                    }

                }
            }
        };

        registerReceiver(broadcastReceiver, filter);

    }
}
