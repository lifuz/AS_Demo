package com.lifuz.testtimertask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button test1_btn,test2_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test1_btn = (Button) findViewById(R.id.test1_btn);
        test1_btn.setOnClickListener(this);

        test2_btn = (Button) findViewById(R.id.test2_btn);
        test2_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.test1_btn:

                startActivity(new Intent(MainActivity.this,Timer1Activity.class));
                break;

            case R.id.test2_btn:

                startActivity(new Intent(MainActivity.this,Timer2Atcivity.class));
                break;


        }
    }
}
