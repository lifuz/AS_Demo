package com.lifuz.testvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * volley的初步使用
 * 详情请参见：
 * http://blog.csdn.net/guolin_blog/article/details/17482095
 * 作者：李富 on 2015/8/11
 * 邮箱：lifuzz@163.com
 */
public class SimpleActivity extends AppCompatActivity {

    private TextView simple_tv;

    RequestQueue mqueue ;//http的请求队列

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_simple);

        simple_tv = (TextView) findViewById(R.id.simple_tv);

        //初始化http请求队列
        mqueue = Volley.newRequestQueue(this);

        //创建http请求
        StringRequest stringRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                simple_tv.setText(s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.e("TAG",volleyError.getMessage(),volleyError);

            }
        });

        mqueue.add(stringRequest);
    }


}
