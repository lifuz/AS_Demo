package com.lifuz.testvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lifuz.testvolley.bean.Login;
import com.lifuz.testvolley.request.MyJsonObjectRequest;

public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://121.40.199.67/TrackServer/login?userName=123&passWord=8";
        final TextView custome_tv = (TextView) findViewById(R.id.volley_custome);

        MyJsonObjectRequest<Login> jsonObjectRequest =new MyJsonObjectRequest<Login>(url,null,
                Login.class, new Response.Listener<Login>() {
            @Override
            public void onResponse(Login login) {

                custome_tv.setText(login.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });


        queue.add(jsonObjectRequest);

    }


}
