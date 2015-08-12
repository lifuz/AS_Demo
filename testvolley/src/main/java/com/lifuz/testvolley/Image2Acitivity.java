package com.lifuz.testvolley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * volley:测试ImageLoader,步骤如下：
 *
 * 1. 创建一个RequestQueue对象。
 * 2. 创建一个ImageLoader对象。
 * 3. 获取一个ImageListener对象。
 * 4. 调用ImageLoader的get()方法加载网络上的图片。
 * 详情请参见：
 * http://blog.csdn.net/guolin_blog/article/details/17482095
 * 作者：李富 on 2015/8/12
 * 邮箱：lifuzz@163.com
 */
public class Image2Acitivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image2_acitivity);

        //1. 创建一个RequestQueue对象。
        RequestQueue mqueue = Volley.newRequestQueue(this);

        imageView = (ImageView) findViewById(R.id.image2_iv);

        //2. 创建一个ImageLoader对象。
        ImageLoader imageLoader = new ImageLoader(mqueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {

            }
        });

        //3. 获取一个ImageListener对象。
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher);

        //4. 调用ImageLoader的get()方法加载网络上的图片。
        //这是不支持缩放
//        imageLoader.get("http://pic1.nipic.com/2008-09-08/200898163242920_2.jpg",listener);

        //缩放图片
        imageLoader.get("http://pic1.nipic.com/2008-09-08/200898163242920_2.jpg",listener,200,200);


    }


}
