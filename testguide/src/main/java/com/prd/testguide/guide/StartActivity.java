package com.prd.testguide.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.prd.testguide.MainActivity;
import com.prd.testguide.R;
import com.prd.testguide.utils.SharePreferencesUtils;

/**
 *
 * app的开始页面
 *
 * 作者：李富 on 2016/3/2.
 * 邮箱：lifuzz@163.com
 */
public class StartActivity extends Activity {

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 0x003) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_start);

        if (SharePreferencesUtils.isFirst(this)) {
            finish();
            startActivity(new Intent(this,GuideActivity.class));
        } else {
            new Thread() {

                @Override
                public void run() {

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.sendEmptyMessage(0x003);

                }
            }.start();
        }




    }

}
