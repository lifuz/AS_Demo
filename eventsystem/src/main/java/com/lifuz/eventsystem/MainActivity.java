package com.lifuz.eventsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lifuz.eventsystem.service.MonitorService;
import com.lifuz.eventsystem.service.MyService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(MainActivity.this, MonitorService.class));

    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.i("service", "服务已经启动");
        if (MyService.isRunning) {
            Log.i("service","服务已经启动");
        }else {
            Log.i("service","启动服务");
            startService(new Intent(MainActivity.this, MyService.class));
        }
    }


}
