volley的使用

具体使用方法请参见:

http://blog.csdn.net/guolin_blog/article/details/17482095


1.volley的简单使用：

    ①创建一个RequestQueue（请求队列对象）：

        RequestQueue mqueue = Volley.newRequestQueue(context);

    ②创建request的请求

        //创建http请求
        StringRequest stringRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                simple_tv.setText(s);

            }

        }, new Response.ErrorListener() {
            //网络访问失败的处理
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.e("TAG",volleyError.getMessage(),volleyError);

            }
        });

       ③把request请求加入RequestQueue，访问网络：

        mqueue.add(stringRequest);

2.StringRequ的post方法测试

代码如下：

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://121.40.199.67/TrackServer/login",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Log.i("tag", "result:" +jsonObject.getString("result"));
                    Log.i("tag", "desc:" +jsonObject.getString("desc"));
                    Log.i("tag", "opid:" +jsonObject.getString("opid"));

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

                Map<String,String> params = new Hashtable<>();
                params.put("userName","123");
                params.put("passWord","8");

                return params;
            }
        };


说明Volley没有可以加载post参数的类，必须重写StringRequest类的getParams()方法来传递参数。

3.测试json，根据返回json类型选择相应的request请求主要有两种：JsonObjectRequest,JsonArrayRequest两种。

我认为这个最好只用作get请求，post请求会丢失标头。

具体代码如下：

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

4.测试图片ImageRequest，具体代码如下：

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

5.测试ImageLoader:具体如下所示

 * 1. 创建一个RequestQueue对象。
 * 2. 创建一个ImageLoader对象。
 * 3. 获取一个ImageListener对象。
 * 4. 调用ImageLoader的get()方法加载网络上的图片。


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

6测试NetworkImageView：实现过程如下


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

7测试自定义Request，详情请参见：MyJsonObjectRequest类, 自定义的Request的两种用法请见：CustomActivity
和Custom2Activity两个类。



源码也学习完了