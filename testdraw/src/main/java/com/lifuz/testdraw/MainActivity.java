package com.lifuz.testdraw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lifuz.testdraw.chart.BarChart;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn =(Button) findViewById(R.id.chart_btn);
        btn.setOnClickListener(this);

        Button mpa = (Button) findViewById(R.id.mpa_btn);
        mpa.setOnClickListener(this);

        Button cb = (Button) findViewById(R.id.cb_btn);
        cb.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chart_btn:

                startActivity(new Intent(MainActivity.this, BarChart.class));
                break;

            case R.id.mpa_btn:

                startActivity(new Intent(MainActivity.this,MPABarChart.class));
                break;

            case R.id.cb_btn:

                startActivity(new Intent(MainActivity.this,CombinedActivity.class));
                break;
        }
    }
}
