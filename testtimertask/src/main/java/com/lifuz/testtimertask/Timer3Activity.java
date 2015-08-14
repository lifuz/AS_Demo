package com.lifuz.testtimertask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 测试第三种实现定时器的方法
 *
 * 作者：李富 on 2015/8/14.
 * 邮箱：lifuzz@163.com
 */
public class Timer3Activity extends Activity implements View.OnClickListener {

    private Button start_btn,pause_btn,stop_btn;
    private TextView timer_tv;

    private static int count = 0;

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timer1_layout);

        initView();

        timer = new Timer();

    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            handler.sendEmptyMessage(1);

        }
    };



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
                //开始计算时间
                timer.schedule(task,1000,1000);

                timer_tv.setTextColor(Color.GREEN);

                break;

            case R.id.pause_btn_1:

                //取消停止计时器，但是不清空count

                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }

                if (task != null) {
                    task.cancel();
                    task = null;
                }
                timer_tv.setTextColor(Color.RED);

                break;
            case  R.id.stop_btn_1:

                //取消停止计时器，并清空已记的时间
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }

                if (task != null) {
                    task.cancel();
                    task = null;
                }
                timer_tv.setTextColor(Color.BLACK);
                count = 0;
                timer_tv.setText(count + "");
                break;
        }

    }
}
