volley的使用

具体使用方法请参见:

http://blog.csdn.net/guolin_blog/article/details/17482095


1.volley的简单使用：

    ①创建一个RequestQueue（请求队列对象）：

        RequestQueue mqueue = Volley.newRequestQueue(context);

    ②创建一个request的请求

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

       ③把request请求放入请求队列，访问网络：

        mqueue.add(stringRequest);

