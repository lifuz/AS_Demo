package com.lifuz.testvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lifuz.testvolley.bean.Car;
import com.lifuz.testvolley.request.MyJsonObjectRequest;

import java.util.HashMap;
import java.util.Map;

public class Custom2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://121.40.199.67/TrackServer/getVehicleCurrentInfo?sgid=284";

        Map<String,String> map = new HashMap<>();
        map.put("sgid","284");

        final TextView custome_tv = (TextView) findViewById(R.id.volley_custome);

        MyJsonObjectRequest<Car[]> jsonObjectRequest =new MyJsonObjectRequest<Car[]>(Request.Method.POST,url,
               map, Car[].class, new Response.Listener<Car[]>() {
            @Override
            public void onResponse(Car[] login) {

                for (Car car: login) {
                    Log.i("tag",car.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        queue.add(jsonObjectRequest);

    }


}
