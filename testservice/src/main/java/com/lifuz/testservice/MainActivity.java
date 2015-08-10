package com.lifuz.testservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lifuz.testservice.com.lifuz.service.MyService;
import com.lifuz.testservice.com.lifuz.service.NotificationService;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start, stop;
    private Button bind, unbind;
    private Button notify,unnotify;

    //定义一个Service绑定类
    private MyService.MyBinder mbind;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //初始化Service绑定类
            mbind = (MyService.MyBinder) service;
            //调用绑定类中的方法并传值
            mbind.startDownload("www.prd.com");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    public void initViews() {

        start = (Button) findViewById(R.id.start_service);
        start.setOnClickListener(this);

        stop = (Button) findViewById(R.id.stop_service);
        stop.setOnClickListener(this);

        bind = (Button) findViewById(R.id.bind_service);
        unbind = (Button) findViewById(R.id.unbind_service);

        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);

        notify = (Button) findViewById(R.id.notify_service);
        notify.setOnClickListener(this);

        unnotify = (Button) findViewById(R.id.unnotify_service);
        unnotify.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start_service:

                startService(new Intent(this, MyService.class));

                break;

            case R.id.stop_service:

                stopService(new Intent(this, MyService.class));

                break;

            case R.id.bind_service:

                bindService(new Intent(this, MyService.class), conn, BIND_AUTO_CREATE);

                break;

            case R.id.unbind_service:

                unbindService(conn);

                break;

            case R.id.notify_service:

                startService(new Intent(this, NotificationService.class));

                break;

            case R.id.unnotify_service:

                stopService(new Intent(this,NotificationService.class));
                break;


        }

    }
}
