package com.lifuz.testvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

/**
 * volley:post方法
 * 详情请参见：
 * http://blog.csdn.net/guolin_blog/article/details/17482095
 * 作者：李富 on 2015/8/11
 * 邮箱：lifuzz@163.com
 */
public class JsonActivity extends AppCompatActivity {

    private RequestQueue mqueue;
    private TextView volley_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        volley_tv = (TextView) findViewById(R.id.volley_json_tv);

        mqueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://121.40.199.67/TrackServer/login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            Log.i("tag", "result:" + jsonObject.getString("result"));
                            Log.i("tag", "desc:" + jsonObject.getString("desc"));
                            Log.i("tag", "opid:" + jsonObject.getString("opid"));

                            volley_tv.setText(s);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<>();
                params.put("userName", "123");
                params.put("passWord", "8");

                return params;
            }
        };

        mqueue.add(stringRequest);

    }

}
