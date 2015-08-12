package com.lifuz.testvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * volley:测试Json
 * 详情请参见：
 * http://blog.csdn.net/guolin_blog/article/details/17482095
 * 作者：李富 on 2015/8/12
 * 邮箱：lifuzz@163.com
 */

public class Json2Activity extends AppCompatActivity {

    private RequestQueue mqueue;
    private TextView json2_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json2);

        mqueue = Volley.newRequestQueue(this);

        json2_tv = (TextView) findViewById(R.id.json2_tv);

        String url = "http://121.40.199.67/TrackServer/login?userName=123&passWord=8";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        json2_tv.setText(jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        mqueue.add(jsonObjectRequest);


    }


}
