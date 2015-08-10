package com.lifuz.testservice.com.lifuz.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lifuz.testservice.MainActivity;
import com.lifuz.testservice.R;

/**
 * 测试带图标的Service
 * <p/>
 * 作者：李富 on 2015/8/7.
 * 邮箱：lifuzz@163.com
 */
public class NotificationService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();



        Intent nit = new Intent(this, MainActivity.class);
        PendingIntent pit = PendingIntent.getActivity(this,0,nit,0);

        Notification notification = new Notification.Builder(this)
                .setTicker("有新消息")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("这是通知的标题")
                .setContentText("这是通知的内容")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pit).build();

        startForeground(1,notification);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("tag","onDestroy()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
