package com.lifuz.testservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lifuz.testservice.com.lifuz.service.MyService;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start,stop;

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

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start_service:

                startService(new Intent(this, MyService.class));

                break;

            case R.id.stop_service:

                stopService(new Intent(this,MyService.class));

                break;

        }

    }
}
