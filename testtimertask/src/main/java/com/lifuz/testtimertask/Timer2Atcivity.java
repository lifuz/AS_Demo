package com.lifuz.testtimertask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 测试第二种方法
 * 实现定时器
 *
 * 作者：李富 on 2015/8/14.
 * 邮箱：lifuzz@163.com
 */
public class Timer2Atcivity extends Activity implements View.OnClickListener {

    private Button start_btn,pause_btn,stop_btn;
    private TextView timer_tv;

    private static int count = 0;
    private int TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timer1_layout);

        initView();

    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                handler.postDelayed(this, TIME);
                count += 1;
                timer_tv.setText(count + "");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("exception...");
            }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.start_btn_1:


                handler.postDelayed(runnable, TIME); //每隔1s执行
                timer_tv.setTextColor(Color.GREEN);

                break;

            case R.id.pause_btn_1:

                handler.removeCallbacks(runnable);
                timer_tv.setTextColor(Color.RED);

                break;
            case  R.id.stop_btn_1:
                handler.removeCallbacks(runnable);
                timer_tv.setTextColor(Color.BLACK);
                count = 0;
                timer_tv.setText(count + "");
                break;
        }
    }
}
