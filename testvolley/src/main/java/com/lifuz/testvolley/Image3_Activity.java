package com.lifuz.testvolley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

/**
 * volley:测试NetworkImageView 组件
 * 详情请参见：
 * http://blog.csdn.net/guolin_blog/article/details/17482095
 * 作者：李富 on 2015/8/12
 * 邮箱：lifuzz@163.com
 */
public class Image3_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image3_);

        NetworkImageView niv = (NetworkImageView) findViewById(R.id.volley_niv_iv);

        RequestQueue mqueue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(mqueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {

            }
        });

        niv.setDefaultImageResId(R.mipmap.ic_launcher);
        niv.setErrorImageResId(R.mipmap.ic_launcher);

        niv.setImageUrl("http://pic1.nipic.com/2008-09-08/200898163242920_2.jpg",imageLoader);

    }


}
