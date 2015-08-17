package com.lifuz.testvolley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.lifuz.testvolley.bean.FormImage;
import com.lifuz.testvolley.bean.FormText;
import com.lifuz.testvolley.request.PostRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试图片上传
 *
 * 作者：李富 on 2015/8/16.
 * 邮箱：lifuzz@163.com
 */
public class UploadActivity extends Activity {

    private Button upload_btn;
    private ImageView upload_iv;

    private String url;
    private RequestQueue queue;
    private ImageLoader imageLoader;

    private ImageLoader.ImageListener listener;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {

                imageLoader.get(url,listener);

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.upload_layout);

        upload_btn = (Button) findViewById(R.id.upload_btn);
        upload_iv = (ImageView) findViewById(R.id.upload_image);

        queue = Volley.newRequestQueue(this);

        imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {

            }
        });

        listener = ImageLoader.getImageListener(upload_iv,R.mipmap.ic_launcher,R.mipmap.ic_launcher);

        FormImage fi = new FormImage();
        fi.setmFileName("volley.png");
        fi.setmMime("image/png");
        fi.setmName("image");
        fi.setmBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.volley));
        List<Object> list = new ArrayList<>();

        list.add(fi);

         url = "http://192.168.2.117:8080/TestImageUp/TestUpload";

        //测试图片上传
//        final PostUploadRequest uploadRequest = new PostUploadRequest(url, list, new Response.Listener() {
//            @Override
//            public void onResponse(Object o) {
//                url = o.toString();
//                handler.sendEmptyMessage(1);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        });

        //测试图片和文字同时上传

        FormText ft = new FormText("name","lifuz");
        list.add(ft);

        final PostRequest postRequest = new PostRequest(url, list, new Response.Listener() {
            @Override
            public void onResponse(Object o) {
                url = o.toString();
                handler.sendEmptyMessage(1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                queue.add(uploadRequest);

                queue.add(postRequest);

            }
        });

    }
}
