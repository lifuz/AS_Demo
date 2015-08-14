package com.lifuz.testvolley;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 测试volley
 * 详情请参见：
 * http://blog.csdn.net/guolin_blog/article/details/17482095
 * 作者：李富 on 2015/8/11
 * 邮箱：lifuzz@163.com
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button volley_simple,volley_json,volley_image;
    private Button json2_tv,volley_custome;
    private Button volley_image2,volley_niv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volley_simple = (Button) findViewById(R.id.volley_simple);
        volley_simple.setOnClickListener(this);

        volley_json = (Button) findViewById(R.id.volley_json);
        volley_json.setOnClickListener(this);

        volley_image = (Button) findViewById(R.id.volley_image);
        volley_image.setOnClickListener(this);

        json2_tv = (Button) findViewById(R.id.volley_json2);
        json2_tv.setOnClickListener(this);

        volley_image2 = (Button) findViewById(R.id.volley_image2);
        volley_image2.setOnClickListener(this);

        volley_niv = (Button) findViewById(R.id.volley_niv);
        volley_niv.setOnClickListener(this);

        volley_custome = (Button) findViewById(R.id.volley_custome_btn);
        volley_custome.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.volley_simple:

                startActivity(new Intent(MainActivity.this,SimpleActivity.class));

                break;

            case R.id.volley_json:

                startActivity(new Intent(MainActivity.this,JsonActivity.class));

                break;

            case R.id.volley_json2:

                startActivity(new Intent(MainActivity.this,Json2Activity.class));

                break;

            case R.id.volley_image:

                startActivity(new Intent(MainActivity.this,ImageActivity.class));

                break;

            case R.id.volley_image2:

                startActivity(new Intent(MainActivity.this,Image2Acitivity.class));

                break;

            case R.id.volley_niv:

                startActivity(new Intent(MainActivity.this,Image3_Activity.class));
                break;

            case R.id.volley_custome_btn:

                startActivity(new Intent(MainActivity.this,Custom2Activity.class));
                break;

        }

    }
}
