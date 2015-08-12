package com.lifuz.testvolley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

/**
 * volley:测试ImageRequest
 * 详情请参见：
 * http://blog.csdn.net/guolin_blog/article/details/17482095
 * 作者：李富 on 2015/8/12
 * 邮箱：lifuzz@163.com
 */
public class ImageActivity extends AppCompatActivity {

    private RequestQueue mqueue;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        mqueue = Volley.newRequestQueue(this);
        imageView = (ImageView) findViewById(R.id.image1_iv);

        //第一个参数图片的路径，第二个参数，图片请求成功的回调，第三第四个参数，指定图片的宽和高，超出指定值，则压缩图片，
        //如果为零，表示不进行压缩，第五个参数，指定图片的颜色属性，第六个参数，图片请求失败的回调。
        final ImageRequest imageRequest = new ImageRequest("http://pic1.nipic.com/2008-09-08/200898163242920_2.jpg",
                new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {

                imageView.setImageBitmap(bitmap);

            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        mqueue.add(imageRequest);

    }

}
