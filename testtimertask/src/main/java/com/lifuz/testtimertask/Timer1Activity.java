package com.lifuz.testtimertask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 测试第一种实现定时器的方法
 *
 * 作者：李富 on 2015/8/14.
 * 邮箱：lifuzz@163.com
 */
public class Timer1Activity extends Activity implements View.OnClickListener {

    private Button start_btn,pause_btn,stop_btn;
    private TextView timer_tv;

    private boolean timeFlag = false;
    private static int count = 0;
    private Thread timerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timer1_layout);

        initView();
    }

    private class TimerThread implements Runnable {


        @Override
        public void run() {
            while (timeFlag) {
                try {
                    Thread.sleep(1000);
                    if (timeFlag)
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initView() {

        start_btn = (Button) findViewById(R.id.start_btn_1);
        start_btn.setOnClickListener(this);

        pause_btn = (Button) findViewById(R.id.pause_btn_1);
        pause_btn.setOnClickListener(this);

        stop_btn = (Button) findViewById(R.id.stop_btn_1);
        stop_btn.setOnClickListener(this);

        timer_tv = (TextView) findViewById(R.id.timer1_tv);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                count += 1;
                timer_tv.setText(count + "");
            }
        }
    };




    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.start_btn_1:

                timeFlag =true;
                timerThread = new Thread(new TimerThread());
                timerThread.start();
                timer_tv.setTextColor(Color.GREEN);

                break;

            case R.id.pause_btn_1:

                timeFlag =false;
                timer_tv.setTextColor(Color.RED);

                break;
            case  R.id.stop_btn_1:

                timeFlag =false;
                timer_tv.setTextColor(Color.BLACK);
                count = 0;
                timer_tv.setText(count + "");
                break;
        }

    }
}
