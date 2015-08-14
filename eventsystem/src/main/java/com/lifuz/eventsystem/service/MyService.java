package com.lifuz.eventsystem.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public static boolean isRunning = false;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("service", "我的服务的包名" + getPackageName());
        isRunning =true;

//        final IntentFilter filter = new IntentFilter();
//        // 屏幕灭屏广播
//        filter.addAction(Intent.ACTION_SCREEN_OFF);
//        // 当长按电源键弹出“关机”对话或者锁屏时系统会发出这个广播
//        // example：有时候会用到系统对话框，权限可能很高，会覆盖在锁屏界面或者“关机”对话框之上，
//        // 所以监听这个广播，当收到时就隐藏自己的对话，如点击pad右下角部分弹出的对话框
//        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
//
//        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String action = intent.getAction();
//
//                if (Intent.ACTION_SCREEN_OFF.equals(action)) {
//                    Log.d("service", "灭屏");
//
//                }else if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(intent.getAction())) {
//                    Log.i("service", "home");
//
//                }
//            }
//        };
//
//        registerReceiver(broadcastReceiver,filter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i("service","关闭服务");
        isRunning = false;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
