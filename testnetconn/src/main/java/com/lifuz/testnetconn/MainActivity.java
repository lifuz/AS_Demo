package com.lifuz.testnetconn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lifuz.testnetconn.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button network_btn,wifi_btn,mobile_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        network_btn = (Button) findViewById(R.id.network_btn);
        network_btn.setOnClickListener(this);

        wifi_btn = (Button) findViewById(R.id.wifi_btn);
        wifi_btn.setOnClickListener(this);

        mobile_btn = (Button) findViewById(R.id.mobile_btn);
        mobile_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.network_btn:

                if(NetworkUtils.isNetworkConnnected(getApplicationContext())) {
                    Log.i("tag","网络连接正常");
                    Toast.makeText(MainActivity.this,"网络连接正常",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"没有网络连接",Toast.LENGTH_SHORT).show();
                    Log.i("tag","没有网络连接");
                }

                break;

            case R.id.wifi_btn:

                if(NetworkUtils.isWiFiConnected(getApplicationContext())) {
                    Log.i("tag","网络连接正常");
                    Toast.makeText(MainActivity.this,"wifi网络连接正常",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"没有wifi连接",Toast.LENGTH_SHORT).show();
                    Log.i("tag","没有网络连接");
                }

                break;

            case R.id.mobile_btn:

                if(NetworkUtils.isMobileConnected(getApplicationContext())) {
                    Log.i("tag","网络连接正常");
                    Toast.makeText(MainActivity.this,"移动网络连接正常",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"没有移动网络连接",Toast.LENGTH_SHORT).show();
                    Log.i("tag","没有网络连接");
                }

                break;

        }

    }
}
